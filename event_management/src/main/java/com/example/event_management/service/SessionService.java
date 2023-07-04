package com.example.event_management.service;

import com.example.event_management.model.LoginResponse;
import com.example.event_management.model.Session;
import com.example.event_management.model.Users;
import com.example.event_management.repository.SessionRepository;
import com.example.event_management.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UsersRepository usersRepository;

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
                .role(usersRepository.findById(session.getUserId()).get().getRole())
                .token(session.getToken())
                .tokenExpiry(session.getTokenExpiry())
                .build();
    }

    @Transactional
    public void deleteSession(String token) {
        sessionRepository.deleteByToken(token);
    }

}
