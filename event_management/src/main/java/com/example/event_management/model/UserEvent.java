package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_status")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "event_id")
    private String eventId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "booked")
    private boolean booked;

    @Column(name = "bookmarked")
    private boolean bookmarked;

    @Column(name = "attended")
    private boolean attended;

}
