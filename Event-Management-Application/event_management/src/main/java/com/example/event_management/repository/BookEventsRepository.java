package com.example.event_management.repository;

import com.example.event_management.model.BookEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookEventsRepository extends JpaRepository<BookEvents, String> {
    BookEvents findByEventIdAndUserId(String eventId, String userId);

    void deleteByEventId(String id);

//    void deleteByEventId(String eventId);

}
