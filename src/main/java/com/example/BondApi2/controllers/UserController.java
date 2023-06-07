package com.example.BondApi2.controllers;


import com.example.BondApi2.models.entities.User;
import com.example.BondApi2.models.requests.CreateUserInput;
import com.example.BondApi2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserInput createUserInput) {
        return new ResponseEntity<>(userService.create(createUserInput), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findUserResponseById(id);

        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User userAux = optionalUser.get();
        userAux.setName(user.getName());
        userAux.setDescription(user.getDescription());
        userAux.setApi_key(user.getApi_key());
        userAux.setPublic_key(user.getPublic_key());
        userService.update(userAux);

        return new ResponseEntity<>(userAux, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
