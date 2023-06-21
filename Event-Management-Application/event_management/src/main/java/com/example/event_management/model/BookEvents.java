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
public class BookEvents {

    @Id
    @Column(name = "id")
    private String id;

//    @ManyToOne
//    @JoinColumn(name = "event_id")
//    public Events events;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    public Users users;

    @Column(name = "event_id")
    private String eventId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "status")
    private String status;

}
