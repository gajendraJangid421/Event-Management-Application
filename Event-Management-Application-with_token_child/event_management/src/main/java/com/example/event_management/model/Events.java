package com.example.event_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Events {

    @Id
    @Column(name = "events_id")
    private String eventsId;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private String date;
    @Column(name = "location")
    private String location;
    @Column(name = "total_seats")
    private int totalSeats;
    @Column(name = "seats_left")
    private int seatsLeft;
    @Column(name = "cost")
    private int cost;
    @Column(name = "description")
    private String description;
    @Column(name = "created_by")
    private String createdBy;

}
