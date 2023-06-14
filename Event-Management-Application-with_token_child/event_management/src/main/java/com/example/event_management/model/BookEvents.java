package com.example.event_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "book")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEvents {

    @Id
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "events_id")
    private String eventsId;
    @Column(name = "users_id")
    private String usersId;
}
