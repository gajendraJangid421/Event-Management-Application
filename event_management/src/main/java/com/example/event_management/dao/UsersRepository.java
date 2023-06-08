package com.example.event_management.dao;

import com.example.event_management.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository <Users, String> {

//    static Users getByUsername(String username);

//    boolean existsByUsername(String username);

//    static Users getByUsername(String username) {
//        if(Users.getUsername().equals(username)){
//            return Users;
//        }
//
//        return null;
//    }
}
