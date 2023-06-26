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

    @Column(name = "booked", columnDefinition = "BIT")
    private boolean booked;

    @Column(name = "bookmarked", columnDefinition = "BIT")
    private boolean bookmarked;

    @Column(name = "attended", columnDefinition = "BIT")
    private boolean attended;

}
