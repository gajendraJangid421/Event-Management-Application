package com.example.event_management.users;

import com.example.event_management.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <Users, Integer> {

    Users findByUsername(String username);

    boolean existsByUsername(String username);

    Object getByUsername(String username);
}
