package com.example.backend_bmi;

import com.example.backend_bmi.model.Category;
import com.example.backend_bmi.model.Post;
import com.example.backend_bmi.model.User;
import com.example.backend_bmi.repository.CategoryRepository;
import com.example.backend_bmi.repository.PostRepository;
import com.example.backend_bmi.repository.UserRepository;
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
    CommandLineRunner initDatabase(PostRepository postRepo, UserRepository userRepo, CategoryRepository catRepo) {
        return args -> {
            postRepo.deleteAll();
            userRepo.deleteAll();
            catRepo.deleteAll();

            catRepo.save(new Category(null, "Bluetooth", "Conectividad inalámbrica"));
            catRepo.save(new Category(null, "Indicadores", "Pantallas de pesaje"));
            catRepo.save(new Category(null, "Impresoras", "Equipos de etiquetado"));

            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<User>> typeReferenceUsers = new TypeReference<List<User>>(){};
            TypeReference<List<Post>> typeReferencePosts = new TypeReference<List<Post>>(){};
            
            InputStream inputStreamUsers = TypeReference.class.getResourceAsStream("/json/users.json");
            InputStream inputStreamPosts = TypeReference.class.getResourceAsStream("/json/posts.json");

            try {
                if (inputStreamUsers != null) {
                    List<User> users = mapper.readValue(inputStreamUsers, typeReferenceUsers);
                    userRepo.saveAll(users);
                    System.out.println("--- Usuarios cargados desde JSON: " + users.size() + " ---");
                }

                if (inputStreamPosts != null) {
                    List<Post> posts = mapper.readValue(inputStreamPosts, typeReferencePosts);
                    postRepo.saveAll(posts);
                    System.out.println("--- Manuales cargados desde JSON: " + posts.size() + " ---");
                }

            } catch (IOException e){
                System.out.println("ERROR al cargar JSON: " + e.getMessage());
            }
        };
    }
}