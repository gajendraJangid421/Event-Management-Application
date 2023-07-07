package com.example.event_management.controller;

import com.example.event_management.model.Events;
import com.example.event_management.service.EventsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/events")
public class EventsController {
    @Autowired
    private EventsService eventsService;

    //for admin and user
    @GetMapping
    public List<Events> findAll() {
        return eventsService.findAll();
    }

    //for admin and user
    @GetMapping(path = "/page")
    @ResponseBody
    public List<Events> findEventsWithPagination(
            @RequestParam(defaultValue = "0", required = false) int offset,
            @RequestParam(defaultValue = "5", required = false) @Max(value = 5) int limit,
            @RequestParam(defaultValue = "name", required = false) String sortBy) {

        return eventsService.findEventsWithPagination(offset, limit, sortBy);
    }

    //for admin
    @GetMapping(path = "/{id}")
    public Events getById(@PathVariable String id){
        return eventsService.getById(id);
    }

    //for admin
    @PostMapping
    public Events save(@Valid @RequestBody Events events){
        return eventsService.save(events);
    }

    //for admin
    @PutMapping(path = "/{id}")
    public Events updateById(@PathVariable String id, @Valid @RequestBody Events events){
        events.setId(id);

        return eventsService.updateById(events);
    }

    //for admin
    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable String id){
        eventsService.deleteById(id);
    }

}