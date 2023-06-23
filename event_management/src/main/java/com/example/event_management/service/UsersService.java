package com.example.event_management.service;

import com.example.event_management.exception.DuplicateUsernameException;
import com.example.event_management.exception.UnAuthorisedException;
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
    private UsersRepository usersRepository;

    private UserEventService userEventService;

    static List<Users> usersList = new ArrayList<>();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    boolean isPasswordCorrect = false;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findByUsername(String username) {
        Users user = usersRepository.findByUsername(username);

        if(Objects.isNull(user)){
            throw new UnAuthorisedException("User not found");
        }

        return user;
    }

    public Users findById(String id) {

        Users user = usersRepository.findById(id).orElseThrow(() -> new UnAuthorisedException("User not found"));

        return user;
    }

    public Users save(Users users){
        if(findByUsername(users.getUsername())!=null){
            throw new DuplicateUsernameException("Existing Username");
        }

        users.setId(UUID.randomUUID().toString());

        users = usersRepository.save(users);

        return users;
    }

    public void deleteById(String id){
        usersRepository.deleteById(id);

        userEventService.deleteByUserId(id);
    }


    public Users update(Users users) {
        Users updateUser = usersRepository.findById(users.getId()).orElseThrow(() -> new UnAuthorisedException("User Id not found"));

        updateUser.setFullName(users.getFullName());
        updateUser.setMobileNumber(users.getMobileNumber());

        return usersRepository.save(updateUser);
    }

    public Users resetPassword(String id, ResetPasswordRequest resetPasswordRequest){
        Users passwordChangedUser = usersRepository.findById(id).orElseThrow(() -> new UnAuthorisedException("User Id not found"));

        isPasswordCorrect = encoder.matches(passwordChangedUser.getPassword(), resetPasswordRequest.getOldPassword());

        if(isPasswordCorrect){
            passwordChangedUser.setPassword(resetPasswordRequest.getNewPassword());
        }else {
            throw new UnAuthorisedException("Old Password is not matching");
        }

        return usersRepository.save(passwordChangedUser);
    }

    public Users authenticateUserUsingEmail(ForgetPassword forgetPassword) {

        Users users = usersRepository.findByEmail(forgetPassword.getEmail());

        if(Objects.isNull(users)) {
            throw new UnAuthorisedException("Email not exists");
        }

        isPasswordCorrect = forgetPassword.getNewPassword().equals(forgetPassword.getConfirmPassword());

        if(isPasswordCorrect){
            users.setPassword(forgetPassword.getNewPassword());
            usersRepository.save(users);
        }else{
            throw new UnAuthorisedException("Password not matching");
        }

        return users;
    }

}
