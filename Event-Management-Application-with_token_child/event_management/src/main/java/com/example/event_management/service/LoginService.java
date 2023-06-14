package com.example.event_management.service;

import com.example.event_management.exception.NotFoundException;
import com.example.event_management.exception.WrongPasswordException;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.repository.SessionRepository;
import com.example.event_management.repository.UsersRepository;
import com.example.event_management.model.LoginRequest;
import com.example.event_management.model.Session;
import com.example.event_management.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public LoginResponse authenticationOfUser(LoginRequest loginRequest){

        Users users = usersRepository.findByUsername(loginRequest.getUsername());

        if(users!=null){
//            boolean isPasswordCorrect = bCrypt.matches(users.getPassword(), login.getPassword());
            boolean isPasswordCorrect = loginRequest.getPassword().equals(users.getPassword());

            if(isPasswordCorrect){

                Session session = Session.builder()
                        .loginId(UUID.randomUUID().toString())
                        .usersId(users.getUsersId())
                        .token(UUID.randomUUID().toString())
                        .tokenExpiry(Timestamp.from(Instant.now()).toString())
                        .build();

                session = sessionRepository.save(session);

                return LoginResponse.builder()
                        .usersId(session.getUsersId())
                        .token(session.getToken())
                        .tokenExpiry(session.getTokenExpiry())
                        .build();


            }else{
                throw new WrongPasswordException("Wrong Password");
           }
        }
        else {
            throw new NotFoundException("Username not found");
        }
 }

//    public boolean checkToken(Session session){
//
//        long differenceInTime = difference(Timestamp.from(Instant.now())+"", session.getTokenExpiry());
//
//        if(differenceInTime< 3600000 * 3){
//            return true;
//        }
//
//        return false;
//    }
//
//    public long difference(String now, String before){
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
//
//        try {
//            Date d1 = sdf.parse(now);
//            Date d2 = sdf.parse(before);
//
//            long differenceInTime = d1.getTime() - d2.getTime();
//
////            System.out.println(d1 + "     " + d2 + "        " + differenceInTime);
////            long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(differenceInTime) % 60;
////            System.out.println(differenceInTime);
//
//            return differenceInTime;
//
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
