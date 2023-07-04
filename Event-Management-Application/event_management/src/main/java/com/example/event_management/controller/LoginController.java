package com.example.event_management.controller;

import com.example.event_management.model.LoginRequest;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.model.Session;
import com.example.event_management.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin(origins = "https://localhost:3000")
public class LoginController {

    @Autowired
    LoginService loginService;

    //for user
    @PostMapping(path = "/login")
    public LoginResponse userLogin(@Valid @RequestBody LoginRequest loginRequest) {

        return loginService.authenticationOfUser(loginRequest);
    }

}
