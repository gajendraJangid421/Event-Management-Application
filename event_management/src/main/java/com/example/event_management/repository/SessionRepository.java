package com.example.event_management.repository;

import com.example.event_management.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {

    Session findByToken(String token);

}
