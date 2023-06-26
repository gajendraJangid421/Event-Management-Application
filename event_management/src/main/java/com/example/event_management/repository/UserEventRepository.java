package com.example.event_management.repository;

import com.example.event_management.model.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepository extends JpaRepository<UserEvent, String> {
    UserEvent findByEventIdAndUserId(String eventId, String userId);

    void deleteByUserId(String id);

    void deleteByEventId(String id);
}
