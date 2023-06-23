package com.example.event_management.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String userId;

    private String token;

    private String tokenExpiry;
}