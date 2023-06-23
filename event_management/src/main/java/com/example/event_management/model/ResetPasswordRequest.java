package com.example.event_management.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {

    @NotBlank(message = "Password can not be null or blank")
    private String oldPassword;

    @NotBlank(message = "Password can not be null or blank")
    private String newPassword;
}