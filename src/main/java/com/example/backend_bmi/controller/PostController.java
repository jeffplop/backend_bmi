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
    public List<Post> getAllPosts() {
        return postService.obtenerTodos();
    }

    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String query) {
        return postService.buscarPorTexto(query);
    }

    @GetMapping("/category/{id}")
    public List<Post> getPostsByCategory(@PathVariable Integer id) {
        return postService.obtenerPorCategoria(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.guardarManual(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post post) {
        return postService.actualizarManual(id, post);
    }

    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable Integer id) {
        return postService.eliminarManual(id);
    }
}