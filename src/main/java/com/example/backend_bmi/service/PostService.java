package com.example.backend_bmi.service;

import com.example.backend_bmi.model.Post;
import com.example.backend_bmi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> obtenerTodos() {
        return postRepository.findAll();
    }

    public List<Post> obtenerPorCategoria(Integer categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    public Post guardarManual(Post post) {
        return postRepository.save(post);
    }

    public void borrarTodo() {
        postRepository.deleteAll();
    }
}