package com.example.event_management.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPassword {

    @NotBlank(message = "Add Email Id!")
    private String email;

    @NotBlank(message = "Add New Password!")
    private String newPassword;

    @NotBlank(message = "Add Confirm Password!")
    private String confirmPassword;

}
