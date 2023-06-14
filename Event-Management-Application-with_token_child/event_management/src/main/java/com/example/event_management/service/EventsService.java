package com.example.event_management.service;

import com.example.event_management.exception.NotFoundException;
import com.example.event_management.model.BookEvents;
import com.example.event_management.model.Bookmark;
import com.example.event_management.model.Events;
import com.example.event_management.model.Users;
import com.example.event_management.repository.BookEventsRepository;
import com.example.event_management.repository.BookmarkRepository;
import com.example.event_management.repository.EventsRepository;
import org.hibernate.result.NoMoreReturnsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private BookEventsRepository bookEventsRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

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
        event.setEventsId(UUID.randomUUID().toString());

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
        event.get().setLocation(events.getLocation());
        event.get().setTotalSeats(events.getTotalSeats());
        event.get().setCost(events.getCost());
        event.get().setDescription(events.getDescription());

        return eventsRepository.save(event.get());
    }

    public void delete(String id) {
        eventsRepository.deleteById(id);
    }

    public BookEvents bookAnEvent(Events events, Users users) {
        BookEvents bookEvents = BookEvents.builder()
                .bookId(UUID.randomUUID().toString())
                .eventsId(events.getEventsId())
                .usersId(users.getUsersId())
                .build();

        bookEvents = bookEventsRepository.save(bookEvents);

        return bookEvents;
    }

    public Bookmark bookmarkAnEvent(Events events, Users users) {
        Bookmark bookmark = Bookmark.builder()
                .bookmarkId(UUID.randomUUID().toString())
                .eventsId(events.getEventsId())
                .usersId(users.getUsersId())
                .build();

        bookmark = bookmarkRepository.save(bookmark);

        return bookmark;
    }
}


//1. create event - post -done
//2. get all event - get -done
//2.1. update the event - put -done
//3. bookmark event - patch event_id, user_id -done(but getting some error)
//4. delete event - delete event_id cascade -done(but left with cascade part)
//5. book an event - patch event_id, user_id -done(but getting some error)

