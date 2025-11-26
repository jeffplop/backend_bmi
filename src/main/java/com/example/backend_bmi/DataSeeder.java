package com.example.backend_bmi;

import com.example.backend_bmi.model.Post;
import com.example.backend_bmi.model.User;
import com.example.backend_bmi.repository.UserRepository;
import com.example.backend_bmi.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(PostService postService, UserRepository userRepository) {
        return args -> {
            postService.borrarTodo();
            userRepository.deleteAll();

            postService.guardarManual(new Post(null, 1, 
                "Manual Indicador LP7516B (Premium)", 
                "Configuración Bluetooth completa.", 
                "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", 
                "2.0", "10/04/2024", "Excell", true, 1)); 

            postService.guardarManual(new Post(null, 1, 
                "Manual WaterProof (Gratis)", 
                "Guía básica de protección IP68.", 
                "http://10.0.2.2:8080/manuales/lp7516.pdf", 
                "1.5", "05/02/2024", "Excell", false, 2));

            postService.guardarManual(new Post(null, 1, 
                "Manual ZT410 (Premium)", 
                "Manual de servicio técnico.", 
                "", 
                "3.0", "22/08/2023", "Zebra", true, 3));

            postService.guardarManual(new Post(null, 1, 
                "Manual FB530", 
                "Configuración de etiquetas.", 
                "", 
                "1.0", "15/01/2024", "Excell", false, 3));

            userRepository.save(new User(null, "Administrador", "admin@bmi.cl", "Admin123!", "+56911111111", "ADMIN"));
            userRepository.save(new User(null, "Jeff Ploop", "jeff@bmi.cl", "Jeff123!", "+56922222222", "USER"));
        };
    }
}