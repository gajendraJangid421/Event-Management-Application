package com.example.event_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Add Event name!")
    private String name;

    @Column(name = "date")
    @NotBlank(message = "Add Date!")
    private String date;

    @Column(name = "time")
    @NotBlank(message = "Add Time!")
    private String time;

    @Column(name = "location")
    @NotBlank(message = "Add Location!")
    private String location;

    @Column(name = "total_seats")
    @NotBlank(message = "Add Number of Seats!")
    private int totalSeats;

    @Column(name = "seats_left")
    private int seatsLeft;

    @Column(name = "created_by")
    private String createdBy;

}
