package com.backend.web.controller;

import com.backend.web.exception.UserNotFoundException;
import com.backend.web.model.User;
import com.backend.web.repository.UserRepository;
import com.backend.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        return userService.updateUser(user, newUser);
    }

    @DeleteMapping("/user/{id}")
    void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
