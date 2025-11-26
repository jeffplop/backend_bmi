package com.example.backend_bmi.service;

import com.example.backend_bmi.model.User;
import com.example.backend_bmi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        System.out.println("--- INICIO ACTUALIZACIÓN USUARIO ---");
        System.out.println("ID Solicitado: " + id);
        System.out.println("Datos Recibidos: " + userDetails.toString());

        try {
            Optional<User> optionalUser = userRepository.findById(id);
            
            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();
                System.out.println("Usuario encontrado en BD: " + existingUser.getEmail());

                if (userDetails.getNombre() != null) existingUser.setNombre(userDetails.getNombre());
                if (userDetails.getEmail() != null) existingUser.setEmail(userDetails.getEmail());
                if (userDetails.getTelefono() != null) existingUser.setTelefono(userDetails.getTelefono());
                
                if (userDetails.getPassword() != null && !userDetails.getPassword().trim().isEmpty()) {
                    System.out.println("Actualizando contraseña...");
                    existingUser.setPassword(userDetails.getPassword());
                }

                User guardado = userRepository.save(existingUser);
                System.out.println("--- ACTUALIZACIÓN EXITOSA ---");
                return guardado;
            } else {
                System.out.println("ERROR: Usuario con ID " + id + " NO existe en la base de datos.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("ERROR CRÍTICO AL GUARDAR: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
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