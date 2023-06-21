package com.example.event_management.repository;

import com.example.event_management.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository <Users, String> {
    Users findByUsername(String username);

    Users findByEmail(String email);
}
