package com.example.event_management.service;

import com.example.event_management.exception.UnAuthorisedException;
import com.example.event_management.model.Events;
import com.example.event_management.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private UserEventService userEventService;


    public List<Events> findAll() {
        return eventsRepository.findAll();
    }

    public Events findById(String id) {
        Events event = eventsRepository.findById(id).orElseThrow(() -> new UnAuthorisedException("Event not found"));

        return event;
    }

    public Events save(Events event) {
        event.setId(UUID.randomUUID().toString());

        event = eventsRepository.save(event);

        return event;
    }

    public Events update(Events events) {
        Events event = eventsRepository.findById(events.getId()).orElseThrow(() -> new UnAuthorisedException("Event not found"));

        event.setName(events.getName());
        event.setDate(events.getDate());
        event.setTime(events.getTime());
        event.setLocation(events.getLocation());
        event.setTotalSeats(events.getTotalSeats());

        return eventsRepository.save(event);
    }

    public void delete(String id) {
        eventsRepository.deleteById(id);

        userEventService.deleteByEventId(id);
    }

}