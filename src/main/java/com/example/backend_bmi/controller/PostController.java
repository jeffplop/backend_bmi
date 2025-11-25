package com.example.backend_bmi.controller;

import com.example.backend_bmi.model.Post;
import com.example.backend_bmi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAll() {
        return postService.obtenerTodos();
    }

    @GetMapping("/category/{id}")
    public List<Post> getByCategory(@PathVariable Integer id) {
        return postService.obtenerPorCategoria(id);
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.guardarManual(post);
    }
}