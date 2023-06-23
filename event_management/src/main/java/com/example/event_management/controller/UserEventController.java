package com.example.event_management.controller;

import com.example.event_management.model.UserEvent;
import com.example.event_management.service.UserEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user-event")
public class UserEventController {

    @Autowired
    UserEventService userEventService;

    //for user
    @PatchMapping(path = "/book")
    public void bookAnEvent(@Valid @RequestBody UserEvent userEvent){
        userEventService.bookAnEvent(userEvent);
    }

    @PatchMapping(path = "/bookmark")
    public void bookmarkAnEvent(@Valid @RequestBody UserEvent userEvent){
        userEventService.bookmarkAnEvent(userEvent);
    }

    @PatchMapping(path = "/attend")
    public void AttendAnEvent(@Valid @RequestBody UserEvent userEvent){
        userEventService.attendAnEvent(userEvent);
    }
}
