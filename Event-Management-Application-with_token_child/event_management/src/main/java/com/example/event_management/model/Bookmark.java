package com.example.event_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "bookmark")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @Id
    @Column(name = "bookmark_id")
    private String bookmarkId;
    @Column(name = "events_id")
    private String eventsId;
    @Column(name = "users_id")
    private String usersId;

}
