package com.example.event_management.service;

import com.example.event_management.exception.UnAuthorisedException;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.model.Session;
import com.example.event_management.model.Users;
import com.example.event_management.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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

    public Session isTokenExpired(String token){
        Session session = sessionRepository.findByToken(token);

        if(Objects.isNull(session)){
            throw new UnAuthorisedException("token not found");
        }

        long differenceInTime = difference(Timestamp.from(Instant.now())+"", session.getTokenExpiry());

        if(differenceInTime < 3600000 * 24){
            return session;
        }

        sessionRepository.deleteById(session.getId());

        return null;
    }

    public long difference(String now, String before){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

        try {
            Date presentDate = sdf.parse(now);
            Date beforeDate = sdf.parse(before);

            return presentDate.getTime() - beforeDate.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
