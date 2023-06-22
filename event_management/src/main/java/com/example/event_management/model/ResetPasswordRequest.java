package com.example.event_management.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {

    private String oldPassword;
    private String newPassword;
}
