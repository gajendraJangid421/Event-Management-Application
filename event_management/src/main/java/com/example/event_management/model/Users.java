package com.example.event_management.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Entity
@Table(name = "users")
public class Users {

    protected Users() {

    }

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

    public Users(String usersId, String username, String password, String fullName, String email, String mobileNumber, String role) {
        super();
        this.usersId = usersId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
//        StringBuilder encryptPassword = new StringBuilder();
//        for(int i=0;i<password.length();i++){
//            if(i%2==0){
//                encryptPassword.append((char) (password.charAt(i)+1));
//            }else{
//                encryptPassword.append((char) (password.charAt(i)-2));
//            }
//        }
//        this.password = encryptPassword.toString();

//        Map encoders = new HashMap<>();
//        encoders.put(password, new BCryptPasswordEncoder());
////        encoders.put("noop", NoOpPasswordEncoder.getInstance());
////        encoders.put(password, new StandardPasswordEncoder());
//
//        PasswordEncoder passwordEncoder =
//                new DelegatingPasswordEncoder(password, encoders);

//        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//        String encryptedPassword = bCrypt.encode(password);

        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersId=" + usersId +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", role='" + role + '\'' +
                '}';
    }
}



