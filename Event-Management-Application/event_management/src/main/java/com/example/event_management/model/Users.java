package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "role")
    private String role;


//    @OneToMany(mappedBy = "users")
//    Set<BookEvents> bookEventsSet;


    public void setPassword(String password) {

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bCrypt.encode(password);
        this.password = encryptedPassword;

//        this.password = password;
    }

}



