package com.example.event_management.controller;

import com.example.event_management.model.BookEvents;
import com.example.event_management.model.Bookmark;
import com.example.event_management.model.Events;
import com.example.event_management.model.Users;
import com.example.event_management.service.EventsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    //for admin and user
    @GetMapping(path = "")
    public List<Events> retrieveAllEventsList() {
        return eventsService.findAll();
    }

    //for admin
    @GetMapping(path = "/{id}")
    public Events retrieveEventById(@PathVariable String id){
        return eventsService.findById(id);
    }

    //for admin and user
    @GetMapping(path = "/event-name/{eventName}")
    public Events retrieveEventByEventName(@PathVariable String eventName){
        return eventsService.findByName(eventName);
    }

    //for admin
    @PostMapping(path = "/add-event")
    public Events save(@Valid @RequestBody Events events){
        return eventsService.save(events);
    }

    //for admin
    @PutMapping(path = "/{id}")
    public Events update(@PathVariable String id, @Valid @RequestBody Events events){
        return eventsService.update(id, events);
    }

    //for admin
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id){
        eventsService.delete(id);
    }

    //for user
    @PostMapping(path = "/book")
    public BookEvents bookAnEvent(@Valid @RequestBody Events events, @Valid @RequestBody Users users){
        return eventsService.bookAnEvent(events, users);
    }

    //for user
    @PostMapping(path = "/bookmark")
    public Bookmark bookmarkAnEvent(@Valid @RequestBody Events events, @Valid @RequestBody Users users){
        return eventsService.bookmarkAnEvent(events, users);
    }

}
