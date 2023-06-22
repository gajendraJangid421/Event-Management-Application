package com.example.event_management.model;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEventRequest  {

    private String eventId;
    private String userId;
    private EventStatus eventStatus;

}
