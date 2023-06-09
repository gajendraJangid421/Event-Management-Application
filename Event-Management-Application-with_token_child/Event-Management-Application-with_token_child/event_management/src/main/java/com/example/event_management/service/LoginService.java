package com.example.event_management.service;

import com.example.event_management.repository.SessionRepository;
import com.example.event_management.repository.UsersRepository;
import com.example.event_management.model.Login;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SessionRepository sessionRepository;

    //Not able to use same method from UsersService because it needs to be in static
    public Users findByUsername(String username) {
        List<Users> usersList = usersRepository.findAll();

        for (Users users : usersList) {
            if (users.getUsername().equals(username)) {
                return users;
            }
        }
        return null;
    }

    public LoginResponse authenticationOfUser(Login login, LoginResponse session){
//        Users users = UsersService.findByUsername(login.getUsername());
//        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

        System.out.println(login.getToken());

        LoginResponse loginResponse = findByToken(login.getToken());

        if(!loginResponse.getToken().equals("630fe22ae051cm")) {
            boolean checkToken = checkToken(loginResponse);

            if (checkToken == true) {
//                System.out.println("checkToken is true");
                return new LoginResponse("Got login", loginResponse.getUsersId(), "session.getToken()");
            }

            sessionRepository.delete(findByToken(login.getToken()));
        }

        Users users = findByUsername(login.getUsername());

        if(users!=null){
//        System.out.println(users.getPassword()+ "           "  + login.getPassword());
//            boolean isPasswordCorrect = bCrypt.matches(users.getPassword(), login.getPassword());
            boolean isPasswordCorrect = login.getPassword().equals(users.getPassword());

            if(isPasswordCorrect){

                session.setLoginId(UUID.randomUUID().toString());
                session.setUsersId(users.getUsersId());
                session.setToken(UUID.randomUUID().toString());
                session.setTokenExpiry(Timestamp.from(Instant.now()).toString());
                session = sessionRepository.save(session);

//                return new LoginResponse(users.getUsername() + " got login");
                return new LoginResponse(users.getUsername() + " got login", users.getUsersId(), session.getToken());
            }else{
//                return new LoginResponse("Wrong Password");
                return new LoginResponse("Wrong password",  "session.getUsersId()", "session.getToken()");
            }
        }

//        return new LoginResponse("Username does not exist");
        return new LoginResponse("Username not exist",  "session.getUsersId()", "session.getToken()");
    }

    public LoginResponse findByToken(String token) {
        List<LoginResponse> sessionList = sessionRepository.findAll();

        for (LoginResponse session : sessionList) {
            if (session.getToken()!=null && session.getToken().equals(token)) {
                return session;
            }
        }
        for (LoginResponse session : sessionList) {
            if (session.getToken()!=null && session.getToken().equals("630fe22ae051cm")) {
                return session;
            }
        }
        System.out.println("Getting an error");
        return null;
    }

    public boolean checkToken(LoginResponse loginResponse){

        long differenceInTime = difference(Timestamp.from(Instant.now())+"", loginResponse.getTokenExpiry());

        if(differenceInTime< 3600 * 3){
            return true;
        }

        return false;
    }

    public long difference(String now, String before){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

        try {
            Date d1 = sdf.parse(now);
            Date d2 = sdf.parse(before);

            long differenceInTime = d1.getTime() - d2.getTime();

//            System.out.println(d1 + "     " + d2 + "        " + differenceInTime);
//            long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(differenceInTime) % 60;
//            System.out.println(differenceInTime);

            return differenceInTime;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
