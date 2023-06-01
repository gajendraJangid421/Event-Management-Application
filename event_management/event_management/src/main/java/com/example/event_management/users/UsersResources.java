package com.example.event_management.users;

import com.example.event_management.jpa.UsersRepository;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users)
public class UsersResources {

    private UsersRepository usersRepository;

    public UsersResources(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    @GetMapping(path = "/get")
    public List<UsersDetails> retrieveAllUsersList() {
        return usersRepository.findAll();
    }

    @PostMapping(path = "/post")
    public ResponseEntity<UsersDetails> createNewUser(@Valid @RequestBody UsersDetails usersDetails) {
        UsersDetails savedUsersDetails = usersRepository.save(usersDetails);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUsersDetails.getUsersId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable int id) {

        usersRepository.deleteById(id);
    }
}
