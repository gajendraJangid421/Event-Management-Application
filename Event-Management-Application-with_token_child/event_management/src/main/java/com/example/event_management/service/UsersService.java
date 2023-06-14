package com.example.event_management.service;

import com.example.event_management.exception.ExistingUsernameException;
import com.example.event_management.exception.NotFoundException;
import com.example.event_management.exception.WrongPasswordException;
import com.example.event_management.model.ForgetPassword;
import com.example.event_management.repository.UsersRepository;
import com.example.event_management.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    static List<Users> usersList = new ArrayList<>();

//    private static int usersCount = 1000;
//        static {
//        usersList.add(new Users(++usersCount, "Gajendra", "G", "J", "email", "123", 432, "user"));
//    }
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findByUsername(String username) {
        Users user = usersRepository.findByUsername(username);

        if(user==null){
            throw new NotFoundException("Username not found");
        }

        return user;
    }

    public Users findById(String id) {

        Optional<Users> user = usersRepository.findById(id);

        if(user.isEmpty()){
            throw new NotFoundException("not");
        }

        return user.get();
    }

    public Users save(Users users){
        if(findByUsername(users.getUsername())!=null){
            throw new ExistingUsernameException("Existing Username");
        }

        users.setUsersId(UUID.randomUUID().toString());

//        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//        String encryptedPassword = bCrypt.encode(users.getPassword());
//        users.setPassword(encryptedPassword);

        users = usersRepository.save(users);
//        usersList.add(users);
        return users;
    }

    public void deleteById(String id){
        usersRepository.deleteById(id);
    }

    public Users update(String id, Users users) {
        Optional<Users> user = usersRepository.findById(id);

        if(user.isEmpty()){
            throw new NotFoundException("User Id not found");
        }

        user.get().setFullName(users.getFullName());
        user.get().setPassword(users.getPassword());
        user.get().setMobileNumber(users.getMobileNumber());

        return usersRepository.save(user.get());
    }

    public Users authenticationOfUserUsingEmail(ForgetPassword forgetPassword) {

        Users users = usersRepository.findByEmail(forgetPassword.getEmail());

        if(users==null) {
            throw new NotFoundException("Email not exists");
        }

        if(forgetPassword.getNewPassword().equals(forgetPassword.getConfirmPassword())){
            users.setPassword(forgetPassword.getNewPassword());
            usersRepository.save(users);
        }else{
            throw new WrongPasswordException("Password not matching");
        }

        return users;
    }

}
