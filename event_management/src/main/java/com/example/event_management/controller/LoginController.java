package com.example.event_management.controller;

import com.example.event_management.model.LoginRequest;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class LoginController {
    @Autowired
    LoginService loginService;

    //for user
    @PostMapping(path = "/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return loginService.authenticateUser(loginRequest);
    }
}

//deletebyemailid not resolved and deletebyuserid able to delete userId and resolved by converting boolean to bit

//resetpassword

//login resolved using @autowired SessionService

//token got resolved by replacing return null to exception and vice versa added @Service