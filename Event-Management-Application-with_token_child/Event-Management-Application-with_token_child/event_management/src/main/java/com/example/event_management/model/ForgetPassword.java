package com.example.event_management.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPassword {

    private String email;
    private String password;
    private String newPassword;
    private String confirmPassword;

}
