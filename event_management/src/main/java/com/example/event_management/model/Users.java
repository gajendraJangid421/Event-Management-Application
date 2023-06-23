package com.example.event_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    @NotBlank(message = "Add unique Username!")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Add Password!")
    @Size(min = 8)
    private String password;

    @Column(name = "full_name")
    @NotBlank(message = "Add Name!")
    private String fullName;

    @Column(name = "email")
    @NotBlank(message = "Add Email!")
    private String email;

    @Column(name = "mobile_number")
    @NotBlank(message = "Add Mobile!")
    private String mobileNumber;

    @Column(name = "role")
    @NotBlank(message = "Choose Role!")
    private String role;

    public void setPassword(String password) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bCrypt.encode(password);

        this.password = encryptedPassword;
    }
}



