package com.example.event_management.repository;

import com.example.event_management.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, String> {

}
