package com.example.event_management.repository;

import com.example.event_management.model.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserEventRepository extends JpaRepository<UserEvent, String> {
    UserEvent findByEventIdAndUserId(String eventId, String userId);

    @Modifying
    @Query("DELETE UserEvent userEvent WHERE userEvent.eventId = :eventId")
    void deleteByEventId(@Param("eventId") String eventId);

    @Modifying
    @Query("DELETE UserEvent userEvent WHERE userEvent.userId = :userId")
    void deleteByUserId(@Param("userId") String userId);
}
