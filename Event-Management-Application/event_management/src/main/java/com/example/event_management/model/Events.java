package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Events {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @Column(name = "location")
    private String location;
    @Column(name = "total_seats")
    private int totalSeats;
    @Column(name = "seats_left")
    private int seatsLeft;
    @Column(name = "description")
    private String description;
    @Column(name = "created_by")
    private String createdBy;

//    @OneToMany(mappedBy = "events")
//    Set<BookEvents> bookEventsSet;
}
