package com.example.event_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "session")
public class LoginResponse {

    @Id
    @Column(name = "login_Id")
    String loginId;
    @Column(name = "users_Id")
    String usersId;
    @Column(name = "token")
    String token;
    @Column(name = "token_expiry")
    String tokenExpiry;


    private String message;
    private boolean status;

//    public Session(String loginId, String usersId, String token, String tokenExpiry) {
//        super();
//        this.loginId = loginId;
//        this.usersId = usersId;
//        this.token = token;
//        this.tokenExpiry = tokenExpiry;
//    }

    LoginResponse(){}

    public LoginResponse(String message) {
        super();
        this.message = message;
//        this.status = status;

        this.loginId = loginId;
        this.usersId = usersId;
        this.token = token;
        this.tokenExpiry = tokenExpiry;
    }

//    public LoginResponse(String message,String loginId, String usersId, String token, String tokenExpiry) {
//        super();
//        this.message = message;
////        this.status = status;
//
//        this.loginId = loginId;
//        this.usersId = usersId;
//        this.token = token;
//        this.tokenExpiry = tokenExpiry;
//    }


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(String tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
//                ", status='" + status + '\'' +
                "loginId='" + loginId + '\'' +
                ", usersId='" + usersId + '\'' +
                ", token='" + token + '\'' +
                ", tokenExpiry='" + tokenExpiry + '\'' +
                '}';
    }

//    @Entity
//    @Table(name = "session")
//    public static class Session {
//
//        @Id
//        @Column(name = "login_Id")
//        String loginId;
//        @Column(name = "users_Id")
//        String usersId;
//        @Column(name = "token")
//        String token;
//        @Column(name = "token_expiry")
//        String tokenExpiry;
//
//        public Session(String loginId, String usersId, String token, String tokenExpiry) {
//            super();
//            this.loginId = loginId;
//            this.usersId = usersId;
//            this.token = token;
//            this.tokenExpiry = tokenExpiry;
//        }
//
//        public String getLoginId() {
//            return loginId;
//        }
//
//        public void setLoginId(String loginId) {
//            this.loginId = loginId;
//        }
//
//        public String getUsersId() {
//            return usersId;
//        }
//
//        public void setUsersId(String usersId) {
//            this.usersId = usersId;
//        }
//
//        public String getToken() {
//            return token;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }
//
//        public String getTokenExpiry() {
//            return tokenExpiry;
//        }
//
//        public void setTokenExpiry(String tokenExpiry) {
//            this.tokenExpiry = tokenExpiry;
//        }
//
//        @Override
//        public String toString() {
//            return "Session{" +
//                    "loginId='" + loginId + '\'' +
//                    ", usersId='" + usersId + '\'' +
//                    ", token='" + token + '\'' +
//                    ", tokenExpiry='" + tokenExpiry + '\'' +
//                    '}';
//        }
//    }
}
