package com.example.event_management.controller;

import com.example.event_management.model.Users;
import com.example.event_management.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.lang.String;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping(path = "")
    public List<Users> retrieveAllUsersList() {
        return usersService.findAll();
    }

    @GetMapping(path = "/{username}")
    public Users retrieveUsersList(@PathVariable String username) {
        return usersService.findByUsername(username);
    }

    @PostMapping(path = "/sign-up")
    public Users signUp(@Valid @RequestBody Users users) {
        return usersService.save(users);
    }

    @DeleteMapping(path = "/{username}")
    public void signOut(@PathVariable String username) {
        usersService.deleteByUsername(username);
    }
}
