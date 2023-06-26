package com.example.event_management.service;

import com.example.event_management.exception.UnAuthorisedException;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.repository.UsersRepository;
import com.example.event_management.model.LoginRequest;
import com.example.event_management.model.Session;
import com.example.event_management.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SessionService sessionService;

    public LoginResponse authenticateUser(LoginRequest loginRequest){

        Session session = sessionService.isTokenExpired(loginRequest.getToken());

        if(Objects.nonNull(session)){
            return LoginResponse.builder()
                    .userId(session.getUserId())
                    .token(session.getToken())
                    .tokenExpiry(session.getTokenExpiry())
                    .build();
        }

        if (Objects.isNull(loginRequest.getUsername()) || Objects.isNull(loginRequest.getPassword())){
            throw new UnAuthorisedException("Username and Password can not be blank");
        }

        Users users = usersRepository.findByUsername(loginRequest.getUsername());

        if(Objects.nonNull(users)){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean isPasswordCorrect = encoder.matches(loginRequest.getPassword(), users.getPassword());

            if(isPasswordCorrect){
               return sessionService.generateSessionForUser(users);
            }
            else{
                throw new UnAuthorisedException("Wrong Password");
           }
        }
        else {
            throw new UnAuthorisedException("Username not found");
        }
   }
}
