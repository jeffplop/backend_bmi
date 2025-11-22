package com.example.backend_bmi.controller;

import com.example.backend_bmi.model.User;
import com.example.backend_bmi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setRole("USER");
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginData) {
        User user = userRepository.findByEmail(loginData.getEmail());
        if (user != null && user.getPassword().equals(loginData.getPassword())) {
            return user;
        }
        return null;
    }
}