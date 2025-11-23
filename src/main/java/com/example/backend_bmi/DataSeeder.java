package com.example.backend_bmi;

import com.example.backend_bmi.model.Post;
import com.example.backend_bmi.model.User;
import com.example.backend_bmi.service.PostService;
import com.example.backend_bmi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(PostService postService, UserService userService) {
        return args -> {
            postService.borrarTodo();
            userService.borrarTodo();

            postService.guardarManual(new Post(null, 1, 
                "Manual Indicador LP7516B (Premium)", 
                "Configuración Bluetooth avanzada.", 
                "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", 
                "2.0", "10/04/2024", "Excell", true, 1));

            postService.guardarManual(new Post(null, 1, 
                "Manual WaterProof (Gratis)", 
                "Guía básica de protección IP68.", 
                "http://10.0.2.2:8080/manuales/prueba.pdf", 
                "1.5", "05/02/2024", "Excell", false, 2));

            postService.guardarManual(new Post(null, 1, 
                "Manual ZT410 (Premium)", 
                "Manual de servicio técnico.", 
                "", 
                "3.0", "22/08/2023", "Zebra", true, 4));

            userService.registrar(new User(null, "Admin", "admin@bmi.cl", "Admin123!", "Oficina", "ADMIN"));
            userService.registrar(new User(null, "Premium User", "premium@bmi.cl", "Premium123!", "Casa", "PREMIUM"));
            userService.registrar(new User(null, "Gratis User", "user@bmi.cl", "User123!", "Taller", "USER"));
        };
    }
}