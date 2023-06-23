package com.example.event_management.service;

import com.example.event_management.model.UserEvent;
import com.example.event_management.repository.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserEventService {

    @Autowired
    UserEventRepository userEventRepository;

    public void deleteByEventId(String id) {
        List<UserEvent> userEventList = userEventRepository.findAll();

        for (UserEvent userEvent : userEventList) {
            if (userEvent.getEventId() != null && userEvent.getEventId().toString().equals(id)) {
                userEventRepository.deleteById(userEvent.getId());
            }
        }
    }

    public void deleteByUserId(String id) {
        List<UserEvent> userEventList = userEventRepository.findAll();

        for (UserEvent userEvent : userEventList) {
            if (userEvent.getUserId() != null && userEvent.getUserId().toString().equals(id)) {
                userEventRepository.deleteById(userEvent.getId());
            }
        }
    }

    public void bookAnEvent(UserEvent userEvent) {

        UserEvent bookEvent = userEventRepository.findByEventIdAndUserId(userEvent.getEventId(), userEvent.getUserId());

        if(Objects.nonNull(bookEvent)){

            if(bookEvent.isBooked()) {
                bookEvent.setBooked(false);
            }else{
                bookEvent.setBooked(true);
            }
        }
        else {
            bookEvent = UserEvent.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(userEvent.getEventId())
                    .userId(userEvent.getUserId())
                    .booked(true)
                    .build();
        }

        userEventRepository.save(bookEvent);
    }

    public void bookmarkAnEvent(UserEvent userEvent) {

        UserEvent bookmarkEvent = userEventRepository.findByEventIdAndUserId(userEvent.getEventId(), userEvent.getUserId());

        if(Objects.nonNull(bookmarkEvent)){

            if(bookmarkEvent.isBookmarked()) {
                bookmarkEvent.setBookmarked(false);
            }else{
                bookmarkEvent.setBookmarked(true);
            }
        }
        else {
            bookmarkEvent = UserEvent.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(userEvent.getEventId())
                    .userId(userEvent.getUserId())
                    .bookmarked(true)
                    .build();
        }

        userEventRepository.save(bookmarkEvent);
    }

    public void attendAnEvent(UserEvent userEvent) {

        UserEvent attendEvent = userEventRepository.findByEventIdAndUserId(userEvent.getEventId(), userEvent.getUserId());

        if(Objects.nonNull(attendEvent)){

            if(attendEvent.isAttended()) {
                attendEvent.setAttended(false);
            }else{
                attendEvent.setAttended(true);
            }
        }
        else {
            attendEvent = UserEvent.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(userEvent.getEventId())
                    .userId(userEvent.getUserId())
                    .attended(true)
                    .build();
        }

        userEventRepository.save(attendEvent);
    }
}
