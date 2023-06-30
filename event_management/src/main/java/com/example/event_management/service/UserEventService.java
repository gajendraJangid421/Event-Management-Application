package com.example.event_management.service;

import com.example.event_management.exception.ObjectValidationException;
import com.example.event_management.model.Events;
import com.example.event_management.model.UserEvent;
import com.example.event_management.repository.EventsRepository;
import com.example.event_management.repository.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserEventService {

    @Autowired
    UserEventRepository userEventRepository;

    @Autowired
    EventsRepository eventsRepository;

    @Test
    @Transactional
    public void deleteByEventId(String eventId) {

        userEventRepository.deleteByEventId(eventId);
    }

    @Test
    @Transactional
    public void deleteByUserId(String userId) {

        userEventRepository.deleteByUserId(userId);
    }

    public void bookAnEvent(UserEvent userEvent) {
        Events event = eventsRepository.findById(userEvent.getEventId()).get();

        if(event.getSeatsLeft()==0){
            throw new ObjectValidationException("'seatsLeft' can not be negative");
        }

        UserEvent bookEvent = userEventRepository.findByEventIdAndUserId(userEvent.getEventId(), userEvent.getUserId());

        if(Objects.nonNull(bookEvent)){

            if(bookEvent.isBooked()) {
                event.setSeatsLeft(event.getSeatsLeft()+1);
            }else{
                event.setSeatsLeft(event.getSeatsLeft()-1);
            }

            bookEvent.setBooked(!bookEvent.isBooked());
        }
        else {
            bookEvent = UserEvent.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(userEvent.getEventId())
                    .userId(userEvent.getUserId())
                    .booked(userEvent.isBooked())
                    .build();

            event.setSeatsLeft(event.getSeatsLeft()-1);
        }

        eventsRepository.save(event);
        userEventRepository.save(bookEvent);
    }

    public void bookmarkAnEvent(UserEvent userEvent) {

        UserEvent bookmarkEvent = userEventRepository.findByEventIdAndUserId(userEvent.getEventId(), userEvent.getUserId());

        if(Objects.nonNull(bookmarkEvent)){
            bookmarkEvent.setBookmarked(!bookmarkEvent.isBookmarked());
        }
        else {
            bookmarkEvent = UserEvent.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(userEvent.getEventId())
                    .userId(userEvent.getUserId())
                    .bookmarked(userEvent.isBookmarked())
                    .build();
        }

        userEventRepository.save(bookmarkEvent);
    }

    public void attendAnEvent(UserEvent userEvent) {

        UserEvent attendEvent = userEventRepository.findByEventIdAndUserId(userEvent.getEventId(), userEvent.getUserId());

        if(Objects.nonNull(attendEvent)){
            attendEvent.setAttended(!attendEvent.isAttended());
        }
        else {
            attendEvent = UserEvent.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(userEvent.getEventId())
                    .userId(userEvent.getUserId())
                    .attended(userEvent.isAttended())
                    .build();
        }

        userEventRepository.save(attendEvent);
    }
}
