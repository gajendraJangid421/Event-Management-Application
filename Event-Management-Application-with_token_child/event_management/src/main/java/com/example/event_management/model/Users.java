package com.example.event_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @Column(name = "users_id")
    private String usersId;
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

//    public void setPassword(String password) {
//
////        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
////        String encryptedPassword = bCrypt.encode(password);
//
//        this.password = password;
//    }

}



