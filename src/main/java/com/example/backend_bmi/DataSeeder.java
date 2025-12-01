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
            ObjectMapper mapper = new ObjectMapper();
            
            TypeReference<List<User>> typeReferenceUsers = new TypeReference<List<User>>(){};
            TypeReference<List<Post>> typeReferencePosts = new TypeReference<List<Post>>(){};
            TypeReference<List<Category>> typeReferenceCategories = new TypeReference<List<Category>>(){};

            InputStream inputStreamUsers = TypeReference.class.getResourceAsStream("/json/users.json");
            InputStream inputStreamPosts = TypeReference.class.getResourceAsStream("/json/posts.json");
            InputStream inputStreamCategories = TypeReference.class.getResourceAsStream("/json/categories.json");

            try {
                if (inputStreamCategories != null && catRepo.count() == 0) {
                    List<Category> categories = mapper.readValue(inputStreamCategories, typeReferenceCategories);
                    catRepo.saveAll(categories);
                }

                if (inputStreamUsers != null && userRepo.count() == 0) {
                    List<User> users = mapper.readValue(inputStreamUsers, typeReferenceUsers);
                    userRepo.saveAll(users);
                }

                if (inputStreamPosts != null && postRepo.count() == 0) {
                    List<Post> posts = mapper.readValue(inputStreamPosts, typeReferencePosts);
                    postRepo.saveAll(posts);
                }

            } catch (IOException e){
                e.printStackTrace();
            }
        };
    }
}