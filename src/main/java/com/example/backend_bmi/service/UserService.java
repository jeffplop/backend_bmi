package com.example.backend_bmi.service;

import com.example.backend_bmi.model.User;
import com.example.backend_bmi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> obtenerTodos() {
        return userRepository.findAll();
    }

    public User registrar(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User actualizar(Integer id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setNombre(userDetails.getNombre());
            user.setEmail(userDetails.getEmail());
            user.setTelefono(userDetails.getTelefono());
            if(userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()){
                user.setPassword(userDetails.getPassword());
            }
            return userRepository.save(user);
        }).orElse(null);
    }

    public boolean eliminar(Integer id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public void borrarTodo() {
        userRepository.deleteAll();
    }
}