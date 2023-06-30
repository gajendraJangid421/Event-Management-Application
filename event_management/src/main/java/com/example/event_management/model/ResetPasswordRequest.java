package com.example.event_management.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {

    private String userId;

    @NotBlank(message = "Password can not be null or blank")
    @Size(min = 8)
    private String oldPassword;

    @NotBlank(message = "Password can not be null or blank")
    @Size(min = 8)
    private String newPassword;
}