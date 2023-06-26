package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booked")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEvents {

    @Id
    @Column(name = "id")
    private String id; //id and one to many
    @Column(name = "event_id")

    private String eventId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "book_event")
    private boolean bookEvent;
    @Column(name = "bookmark")
    private boolean bookmark;

    public boolean getBookEvent() {
        return bookEvent;
    }

    public boolean getBookmark() {
        return bookmark;
    }
}
