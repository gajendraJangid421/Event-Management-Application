package com.example.event_management.service;

import com.example.event_management.exception.UnAuthorisedException;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.model.Session;
import com.example.event_management.model.Users;
import com.example.event_management.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public LoginResponse generateSessionForUser(Users users) {

        Session session = Session.builder()
                .id(UUID.randomUUID().toString())
                .userId(users.getId())
                .token(UUID.randomUUID().toString())
                .tokenExpiry(Timestamp.from(Instant.now()).toString())
                .build();

        session = sessionRepository.save(session);

        return LoginResponse.builder()
                .userId(session.getUserId())
                .token(session.getToken())
                .tokenExpiry(session.getTokenExpiry())
                .build();
    }

    public void deleteSession(String token) {
        sessionRepository.deleteById(sessionRepository.findByToken(token).getId());
    }

}
