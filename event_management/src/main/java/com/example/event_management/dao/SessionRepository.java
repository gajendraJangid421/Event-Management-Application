package com.example.event_management.dao;

import com.example.event_management.model.LoginResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<LoginResponse, String> {

}
