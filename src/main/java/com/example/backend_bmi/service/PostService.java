package com.example.backend_bmi.service;

import com.example.backend_bmi.exception.ResourceNotFoundException;
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

    public Post obtenerPorId(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manual no encontrado con ID: " + id));
    }

    public List<Post> obtenerPorCategoria(Integer categoryId) {
        List<Post> posts = postRepository.findByCategoryId(categoryId);
        if (posts.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron manuales para la categoría ID: " + categoryId);
        }
        return posts;
    }

    public List<Post> buscarPorTexto(String query) {
        return postRepository.findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(query, query);
    }

    public Post guardarManual(Post post) {
        return postRepository.save(post);
    }

    public Post actualizarManual(Integer id, Post postDetails) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(postDetails.getTitle());
            post.setBody(postDetails.getBody());
            post.setPdfUrl(postDetails.getPdfUrl());
            post.setVersion(postDetails.getVersion());
            post.setFecha(postDetails.getFecha());
            post.setFabricante(postDetails.getFabricante());
            post.setIsPremium(postDetails.getIsPremium());
            post.setCategoryId(postDetails.getCategoryId());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("Manual no encontrado con ID: " + id));
    }

    public boolean eliminarManual(Integer id) {
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("No se puede eliminar. Manual no encontrado con ID: " + id);
        }
    }

    public void borrarTodo() {
        postRepository.deleteAll();
    }
}