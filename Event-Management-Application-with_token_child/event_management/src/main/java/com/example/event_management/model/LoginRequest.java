package com.example.event_management.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull(message = "Username can not be empty")
    private String username;

    @NotNull(message = "Password can not be empty")
    private String password;


}
