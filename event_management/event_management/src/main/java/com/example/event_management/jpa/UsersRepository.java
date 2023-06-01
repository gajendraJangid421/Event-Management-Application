package com.example.event_management.jpa;

import com.example.event_management.users.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersDetails, Integer> {

}
