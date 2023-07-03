package com.example.event_management.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPassword {

    @NotBlank(message = "Add Email Id!")
    private String email;

    @NotBlank(message = "Add New Password!")
    @Size(min = 8)
    private String newPassword;

    @NotBlank(message = "Add Confirm Password!")
    @Size(min = 8)
    private String confirmPassword;

}
