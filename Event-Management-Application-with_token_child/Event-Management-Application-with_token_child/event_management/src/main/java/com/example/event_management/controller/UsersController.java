package com.example.event_management.controller;

import com.example.event_management.model.ForgetPassword;
import com.example.event_management.model.Users;
import com.example.event_management.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    //for admin
    @GetMapping(path = "")
    public List<Users> retrieveAllUsersList() {
        return usersService.findAll();
    }

    //for admin and user
    @GetMapping(path = "/username/{username}")
    public Users retrieveUserByUsername(@PathVariable String username) {
        return usersService.findByUsername(username);
    }

    //for admin
    @GetMapping(path = "/{id}")
    public Users retrieveUserById(@PathVariable String id) {
        return usersService.findById(id);
    }

    //for user
    @PostMapping(path = "/sign-up")
    public Users signUp(@Valid @RequestBody Users users) {
        return usersService.save(users);
    }

    //for user and admin
    @DeleteMapping(path = "/{username}")
    public void delete(@PathVariable String id) {
        usersService.deleteById(id);
    }

    //for user
    @PatchMapping(path = "/{id}")
    public Users update(@PathVariable(value = "id") String userId, @Valid @RequestBody Users users){

        return usersService.update(userId, users);
    }

    //for user
    @PatchMapping(path = "/forget")
    public Users forgetPassword(@Valid @RequestBody ForgetPassword forgetPassword) {

        return usersService.authenticationOfUserUsingEmail(forgetPassword);
    }

}
