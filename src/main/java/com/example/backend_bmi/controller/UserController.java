package com.example.backend_bmi.controller;

import com.example.backend_bmi.model.User;
import com.example.backend_bmi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.obtenerTodos();
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registrar(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }
}