package com.example.backend_bmi;

import com.example.backend_bmi.model.Post;
import com.example.backend_bmi.model.User;
import com.example.backend_bmi.repository.PostRepository;
import com.example.backend_bmi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(PostRepository postRepository, UserRepository userRepository) {
        return args -> {
            postRepository.deleteAll();
            userRepository.deleteAll();

            postRepository.save(new Post(null, 1,
                "Manual Indicador LP7516B",
                "Indicador de pesaje con módulo Bluetooth 4.0 integrado para conexión inalámbrica.",
                "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                "2.0", "10/04/2024", "Excell"));

            postRepository.save(new Post(null, 1,
                "Manual Indicador WaterProof",
                "Equipo con protección IP68, ideal para ambientes húmedos y lavados frecuentes.",
                "http://10.0.2.2:8080/manuales/prueba.pdf",
                "1.5", "05/02/2024", "Excell"));

            postRepository.save(new Post(null, 1,
                "Manual Indicador LP7516",
                "Versión estándar LCD. Guía de configuración básica y calibración de cero y span.",
                "http://10.0.2.2:8080/manuales/prueba.pdf",
                "1.2", "12/11/2023", "Excell"));

            postRepository.save(new Post(null, 1,
                "Manual Indicador FB530",
                "Indicador con impresora térmica incorporada. Configuración de formatos de etiqueta.",
                "",
                "3.0", "22/08/2023", "Excell"));

            userRepository.save(new User(null, "Administrador", "admin@bmi.cl", "Admin123!", "Oficina Central", "ADMIN"));
            userRepository.save(new User(null, "Jeff Ploop", "jeff@bmi.cl", "Jeff123!", "Av. Siempreviva 742", "USER"));
        };
    }
}