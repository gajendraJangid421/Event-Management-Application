package com.example.event_management.repository;

import com.example.event_management.model.BookEvents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookEventsRepository extends JpaRepository<BookEvents, String> {
}
