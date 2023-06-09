package com.example.event_management.service;

import com.example.event_management.repository.UsersRepository;
import com.example.event_management.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    static List<Users> usersList = new ArrayList<>();
    private static int usersCount = 1000;

    //    static {
//        usersList.add(new Users(++usersCount, "Gajendra", "G", "J", "email", "123", 432, "user"));
//    }
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findByUsername(String username) {
        usersList = usersRepository.findAll();

        for (Users users : usersList) {
            if (users.getUsername().equals(username)) {
                return users;
            }
        }
        return null;
    }

//    public static Users getByUsername(String username){
//        Predicate<? super Users> predicate = usersList -> {
//            return Objects.equals(usersList.getUsername(), username);
//        };
//        System.out.println(predicate);
//        return usersList.stream().filter(predicate).findFirst().orElse(null);
//    }

    public Users save(Users users){
//        users.setUsersId(++usersCount);
        users.setUsersId(UUID.randomUUID().toString());

//        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//        String encryptedPassword = bCrypt.encode(users.getPassword());
//        users.setPassword(encryptedPassword);

        users = usersRepository.save(users);
//        usersList.add(users);
        return users;
    }

    public void deleteByUsername(String username){
        usersRepository.delete(findByUsername(username));
    }

}
