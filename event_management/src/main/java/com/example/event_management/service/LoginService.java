package com.example.event_management.service;

import com.example.event_management.dao.SessionRepository;
import com.example.event_management.dao.UsersRepository;
import com.example.event_management.model.Login;
import com.example.event_management.model.LoginResponse;
import com.example.event_management.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SessionRepository sessionRepository;
//    private Session session;
//    private List<Session> sessionList = new ArrayList<>();
//    boolean flag = false;

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
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

        Users users = findByUsername(login.getUsername());

        if(users!=null){
//        System.out.println(users.getPassword()+ "           "  + login.getPassword());
//            boolean isPasswordCorrect = bCrypt.matches(users.getPassword(), login.getPassword());
            boolean isPasswordCorrect = login.getPassword().equals(users.getPassword());

            if(isPasswordCorrect){

//                session.setLoginId(UUID.randomUUID().toString());
//                session.setUsersId(users.getUsersId());
//                session.setTokenExpiry(Timestamp.from(Instant.now()).toString());
//                session = sessionRepository.save(session);


                return new LoginResponse(users.getUsername() + " got login");
//                return new LoginResponse(users.getUsername() + " got login",  session.getLoginId(), users.getUsersId(), "session.getToken()", session.getTokenExpiry() + "");
            }else{
                return new LoginResponse("Wrong Password");
//                return new LoginResponse("wrong password",  "session.getLoginId()", "session.getUsersId()", "session.getToken()", "session.getTokenExpiry()");
            }
        }

        return new LoginResponse("Username does not exist");
//        return new LoginResponse("not exits",  "session.getLoginId()", "session.getUsersId()", "session.getToken()", "session.getTokenExpiry()");
    }

}
