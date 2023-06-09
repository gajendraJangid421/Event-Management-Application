package com.example.event_management.model;

public class Login {

    private String usersId;
    private String username;
    private String password;
    private String token;


    public Login(String usersId, String username, String password, String token) {
        this.usersId = usersId;
        this.username = username;
        this.password = password;
        this.token = token;
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
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UsersLogin{" +
                "username='" + username + '\'' +
                '}';
    }
}
