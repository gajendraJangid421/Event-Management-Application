package com.example.event_management.controller;

import com.example.event_management.model.ForgetPassword;
import com.example.event_management.model.ResetPasswordRequest;
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
    @GetMapping
    public List<Users> findAll() {
        return usersService.findAll();
    }

    //for admin and user
    @GetMapping(path = "/{id}")
    public Users findById(@PathVariable String id) {
        return usersService.findById(id);
    }

    //for user
    @PostMapping
    public Users save(@Valid @RequestBody Users users) {
        return usersService.save(users);
    }

    //for user and admin
    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable String id) {
        usersService.deleteById(id);
    }

    //for user
    @PatchMapping(path = "/{id}")
    public Users UpdateById(@PathVariable(value = "id") String userId, @Valid @RequestBody Users users){
        users.setId(userId);

        return usersService.update(users);
    }

    //for user
    @PatchMapping(path = "/reset-password/{id}")
    public Users resetPassword(@PathVariable(value = "id") String userId, @Valid @RequestBody ResetPasswordRequest resetPasswordRequest){

        resetPasswordRequest.setUserId(userId);

        return usersService.resetPassword(resetPasswordRequest);
    }

    //for user
    @PatchMapping(path = "/forget-password")
    public Users forgetPassword(@Valid @RequestBody ForgetPassword forgetPassword) {

        return usersService.authenticateUserUsingEmail(forgetPassword);
    }
}