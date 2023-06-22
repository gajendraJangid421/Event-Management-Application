package com.example.event_management.service;

import com.example.event_management.exception.ExistingUsernameException;
import com.example.event_management.exception.NotFoundException;
import com.example.event_management.model.BookEventRequest;
import com.example.event_management.model.BookEvents;
import com.example.event_management.model.Events;
import com.example.event_management.repository.BookEventsRepository;
import com.example.event_management.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private BookEventsRepository bookEventsRepository;


    public List<Events> findAll() {
        return eventsRepository.findAll();
    }

    public Events findById(String id) {
        Events event = eventsRepository.findById(id).orElseThrow(() -> new NotFoundException("Event Id not found"));

        return event;
    }

//    public Events findByName(String eventName) {
//        Events event = eventsRepository.findByName(eventName);
//
//        if (Objects.isNull(event)) {
//            throw new NotFoundException("Event not found");
//        }
//
//        return event;
//    }

    public Events save(Events event) {
        event.setId(UUID.randomUUID().toString());

        event = eventsRepository.save(event);

        return event;
    }

    public Events update(String id, Events events) {
        Events event = eventsRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));

        event.setName(events.getName());
        event.setDate(events.getDate());
        event.setTime(events.getTime());
        event.setLocation(events.getLocation());
        event.setTotalSeats(events.getTotalSeats());
        event.setDescription(events.getDescription());

        return eventsRepository.save(event);
    }

    public List<BookEvents> findAllBook() {
        return bookEventsRepository.findAll();
    }

    public void delete(String id) {
        eventsRepository.deleteById(id);

        List<BookEvents> bookEventsList = findAllBook();

        for (BookEvents bookEvents : bookEventsList) {
            if (bookEvents.getEventId() != null && bookEvents.getEventId().toString().equals(id)) {
                bookEventsRepository.deleteById(bookEvents.getId());
            }
        }
    }

    public BookEvents bookAnEvent(BookEventRequest bookEventRequest) {

        BookEvents bookEvent = bookEventsRepository.findByEventIdAndUserId(bookEventRequest.getEventId(), bookEventRequest.getUserId());

        if(Objects.nonNull(bookEvent) && bookEvent.getStatus().equals("BOOKMARKED")){
            if(bookEventRequest.getEventStatus().getValue().equals("BOOKMARKED")) {
                bookEventsRepository.deleteById(bookEvent.getId());
                return null;
            }else{
                bookEvent.setStatus(bookEventRequest.getEventStatus().getValue());
                bookEventsRepository.save(bookEvent);
                return bookEvent;
            }
        }
        else {
            bookEvent = BookEvents.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(bookEventRequest.getEventId())
                    .userId(bookEventRequest.getUserId())
                    .status(bookEventRequest.getEventStatus().getValue())
                    .build();
        }

        bookEvent = bookEventsRepository.save(bookEvent);

        return bookEvent;
    }

}
