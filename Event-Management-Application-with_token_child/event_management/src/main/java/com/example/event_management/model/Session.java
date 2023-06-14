package com.example.event_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "session")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @Column(name = "login_Id")
    String loginId;
    @Column(name = "users_Id")
    String usersId;
    @Column(name = "token")
    String token;
    @Column(name = "token_expiry")
    String tokenExpiry;

}
