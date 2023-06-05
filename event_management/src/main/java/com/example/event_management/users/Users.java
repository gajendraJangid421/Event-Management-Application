package com.example.event_management.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity(name = "users_details")
public class Users {

    protected Users() {

    }

    @Id
    @GeneratedValue
    private Integer usersId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int mobileNumber;
    private String role;

    public Users(int usersId, String username, String password, String firstName, String lastName, String email, int mobileNumber, String role) {
        super();
        this.usersId = usersId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
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

    public void setPassword(String password){
//        StringBuilder encryptPassword = new StringBuilder();
//        for(int i=0;i<password.length();i++){
//            if(i%2==0){
//                encryptPassword.append((char) (password.charAt(i)+1));
//            }else{
//                encryptPassword.append((char) (password.charAt(i)-2));
//            }
//        }
//        this.password = encryptPassword.toString();
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
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
                ", name='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", role='" + role + '\'' +
                '}';
    }


}
