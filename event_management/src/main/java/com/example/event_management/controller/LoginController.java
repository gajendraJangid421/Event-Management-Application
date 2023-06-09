package com.example.event_management.controller;

import com.example.event_management.model.Login;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/users")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> usersLogin(@Valid @RequestBody Login login, LoginResponse session) {

//        loginService = new LoginService();
        LoginResponse loginResponse = loginService.authenticationOfUser(login, session);

        return ResponseEntity.ok(loginResponse);
    }
}
