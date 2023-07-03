package com.example.event_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    @NotBlank(message = "'name' should not be null or blank")
    private String name;

    @Column(name = "date")
    @NotNull(message = "'date' should not be null")
    private LocalDate date;

    @Column(name = "time")
    @NotNull(message = "'time' should not be null")
    private LocalTime time;

    @Column(name = "location")
    @NotBlank(message = "'location' should not be null or blank")
    private String location;

    @Column(name = "total_seats")
    @Min(1)
    private int totalSeats;

    @Column(name = "seats_left")
    private int seatsLeft;

    @Column(name = "created_by")
    private String createdBy;

}

