package com.example.event_management.service;

import com.example.event_management.exception.ExistingUsernameException;
import com.example.event_management.exception.NotFoundException;
import com.example.event_management.exception.WrongPasswordException;
import com.example.event_management.model.ForgetPassword;
import com.example.event_management.model.ResetPasswordRequest;
import com.example.event_management.repository.UsersRepository;
import com.example.event_management.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    static List<Users> usersList = new ArrayList<>();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    boolean isPasswordCorrect = false;

//    private static int usersCount = 1000;
//        static {
//        usersList.add(new Users(++usersCount, "Gajendra", "G", "J", "email", "123", 432, "user"));
//    }
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findByUsername(String username) {
        Users user = usersRepository.findByUsername(username);

        if(Objects.nonNull(user)){
            throw new NotFoundException("Username not found");
        }

        return user;
    }

    public Users findById(String id) {

        Users user = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        return user;
    }

    public Users save(Users users){
        if(findByUsername(users.getUsername())!=null){
            throw new ExistingUsernameException("Existing Username");
        }

        users.setId(UUID.randomUUID().toString());

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
        Users updateUser = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User Id not found"));

        updateUser.setFullName(users.getFullName());
        updateUser.setMobileNumber(users.getMobileNumber());

        return usersRepository.save(updateUser);
    }

    public Users resetPassword(String id, ResetPasswordRequest resetPasswordRequest){
        Users passwordChangedUser = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User Id not found"));

        isPasswordCorrect = encoder.matches(passwordChangedUser.getPassword(), resetPasswordRequest.getOldPassword());
//        boolean isPasswordCorrect = resetPasswordRequest.getOldPassword().equals(passwordChangedUser.getPassword())

        if(isPasswordCorrect){
            passwordChangedUser.setPassword(resetPasswordRequest.getNewPassword());
        }else {
            throw new WrongPasswordException("Old Password is not matching");
        }

        return usersRepository.save(passwordChangedUser);
    }

    public Users authenticationOfUserUsingEmail(ForgetPassword forgetPassword) {

        Users users = usersRepository.findByEmail(forgetPassword.getEmail());

        if(Objects.nonNull(users)) {
            throw new NotFoundException("Email not exists");
        }

        isPasswordCorrect = forgetPassword.getNewPassword().equals(forgetPassword.getConfirmPassword());

        if(isPasswordCorrect){
            users.setPassword(forgetPassword.getNewPassword());
            usersRepository.save(users);
        }else{
            throw new WrongPasswordException("Password not matching");
        }

        return users;
    }

}
