package com.example.backend_bmi;

import com.example.backend_bmi.model.Category;
import com.example.backend_bmi.model.Post;
import com.example.backend_bmi.model.User;
import com.example.backend_bmi.repository.CategoryRepository;
import com.example.backend_bmi.repository.UserRepository;
import com.example.backend_bmi.service.PostService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(PostService postService, UserRepository userRepository, CategoryRepository categoryRepository) {
        return args -> {
            postService.borrarTodo();
            userRepository.deleteAll();
            categoryRepository.deleteAll();

            categoryRepository.save(new Category(null, "Indicadores", "Pantallas de pesaje estándar"));
            categoryRepository.save(new Category(null, "Impresoras", "Equipos de etiquetado"));
            categoryRepository.save(new Category(null, "Bluetooth", "Conectividad inalámbrica"));

            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<User>> typeReferenceUsers = new TypeReference<List<User>>(){};
            TypeReference<List<Post>> typeReferencePosts = new TypeReference<List<Post>>(){};
            InputStream inputStreamUsers = TypeReference.class.getResourceAsStream("/json/users.json");
            InputStream inputStreamPosts = TypeReference.class.getResourceAsStream("/json/posts.json");

            try {
                List<User> users = mapper.readValue(inputStreamUsers, typeReferenceUsers);
                userRepository.saveAll(users);
                System.out.println("Usuarios cargados: " + users.size());

                List<Post> posts = mapper.readValue(inputStreamPosts, typeReferencePosts);
                for (Post post : posts) {
                    postService.guardarManual(post);
                }
                System.out.println("Manuales cargados: " + posts.size());
            } catch (IOException e){
                System.out.println("Error al cargar JSON: " + e.getMessage());
            }
        };
    }
}