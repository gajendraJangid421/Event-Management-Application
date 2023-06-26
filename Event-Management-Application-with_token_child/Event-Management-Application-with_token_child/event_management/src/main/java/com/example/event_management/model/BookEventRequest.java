package com.example.event_management.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEventRequest{

    private String eventId;
    private String userId;
}