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
import java.util.Optional;
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
        Optional<Events> event = eventsRepository.findById(id);

        if(event.isEmpty()){
            throw new NotFoundException("Event Id not found");
        }

        return event.get();
    }

    public Events findByName(String eventName) {
        Events event = eventsRepository.findByName(eventName);

        if (event == null) {
            throw new NotFoundException("Event not found");
        }

        return event;
    }

    public Events save(Events event) {
        event.setId(UUID.randomUUID().toString());

        event = eventsRepository.save(event);

        return event;
    }

    public Events update(String id, Events events) {
        Optional<Events> event = eventsRepository.findById(id);

        if(event.isEmpty()){
            throw new NotFoundException("Event Id not found");
        }

        event.get().setName(events.getName());
        event.get().setDate(events.getDate());
        event.get().setTime(events.getTime());
        event.get().setLocation(events.getLocation());
        event.get().setTotalSeats(events.getTotalSeats());
        event.get().setDescription(events.getDescription());

        return eventsRepository.save(event.get());
    }

    public List<BookEvents> findAllBook() {
        return bookEventsRepository.findAll();
    }

    public void delete(String id) {
        eventsRepository.deleteById(id);

        List<BookEvents> bookEventsList = findAllBook();

        for(BookEvents bookEvents: bookEventsList){
            if(bookEvents.getEventId()!=null && bookEvents.getEventId().toString().equals(id)){
                bookEventsRepository.deleteById(bookEvents.getId());
            }
        }
    }

    public BookEvents bookAnEvent(BookEventRequest bookEventRequest) {
        
        BookEvents bookEvent = bookEventsRepository.findByEventIdAndUserId(bookEventRequest.getEventId(), bookEventRequest.getUserId());

        if(bookEvent!=null && bookEvent.getBookmark()==true){

            bookEvent.setBookEvent(true);
            bookEvent.setBookmark(false);
        }
        else if(bookEvent!=null && bookEvent.getBookEvent()==true){
            throw new ExistingUsernameException("It is already booked");
        }
        else {
            bookEvent = BookEvents.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(bookEventRequest.getEventId())
                    .userId(bookEventRequest.getUserId())
                    .bookEvent(true)
                    .build();
        }

        bookEvent = bookEventsRepository.save(bookEvent);

        return bookEvent;
    }

    public BookEvents bookmarkAnEvent(BookEventRequest bookEventRequest) {

        BookEvents bookmark = bookEventsRepository.findByEventIdAndUserId(bookEventRequest.getEventId(), bookEventRequest.getUserId());

        if(bookmark!=null && bookmark.getBookEvent()==true){
            throw new ExistingUsernameException("It is already booked. Can not be bookmarked");
        }
        else if(bookmark!=null && bookmark.getBookmark()==true){
            bookEventsRepository.deleteById(bookmark.getId());
            return null;
        }
        else {
            bookmark = BookEvents.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(bookEventRequest.getEventId())
                    .userId(bookEventRequest.getUserId())
                    .bookmark(true)
                    .build();
        }

        bookmark = bookEventsRepository.save(bookmark);

        return bookmark;

    }
}

/*
1. changed naming convection from event_id -> id and users_id -> user_id
2. added oldPassword and newPassword for updating user
3. cascade of events but not by cascade by findAll and delete
 */

