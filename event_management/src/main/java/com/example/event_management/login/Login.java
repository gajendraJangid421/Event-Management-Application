package com.example.event_management.login;

public class Login {

    private int usersId;
    private String username;
    private String password;

    public Login(int usersId, String username, String password) {
        this.usersId = usersId;
        this.username = username;
        this.password = password;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
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

    @Override
    public String toString() {
        return "UsersLogin{" +
                "username='" + username + '\'' +
                '}';
    }
}
