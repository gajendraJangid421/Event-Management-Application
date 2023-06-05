package com.example.event_management.login;

import com.example.event_management.users.UsersRepository;
import com.example.event_management.users.Users;

import java.util.Optional;

public class LoginService {

    private UsersRepository usersRepository;
    boolean isUsernameExists = false;
    public LoginService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }


    Users findByIdAndUsername(String username){
        isUsernameExists = usersRepository.existsByUsername(username);

//        if(usersRepository.getByUsername()!=null && usersRepository.getByUsername().getUsername.equals(username)){
//            isUsernameExists = true;
//        }

        if(isUsernameExists) {
            Optional<Users> usersByUsername = Optional.ofNullable(usersRepository.findByUsername(username));
            return usersByUsername.get();
        }

        return null;
//        return new UsersDetails(id, "Gajendra", "G", "J", "email", "user", 123, "user");
    }

    public LoginResponse usersLogin(Login login){
        Users users = findByIdAndUsername(login.getUsername());

        if(users !=null){
            boolean isPasswordCorrect = login.getPassword().equals(users.getPassword());

            if(isPasswordCorrect){
                return new LoginResponse(users.getUsername() + " got login", true);
            }else{
                return new LoginResponse("Wrong Password", false);
            }
        }

        return new LoginResponse("Username not existing", false);
    }

}
