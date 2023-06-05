package com.example.event_management.users;

import com.example.event_management.login.LoginService;
import com.example.event_management.login.Login;
import com.example.event_management.login.LoginResponse;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    @GetMapping(path = "/get")
    public List<Users> retrieveAllUsersList() {        return usersRepository.findAll();
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<Users> createNewUser(@Valid @RequestBody Users usersDetails) {
        Users savedUsers = usersRepository.save(usersDetails);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUsers.getUsersId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/sign-out/{id}")
    public void deleteUser(@PathVariable int id) {

        usersRepository.deleteById(id);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> usersLogin(@Valid @RequestBody Login login) {

        LoginService instance = new LoginService(usersRepository);
        LoginResponse loginResponse = instance.usersLogin(login);

        return ResponseEntity.ok(loginResponse);
    }
}
