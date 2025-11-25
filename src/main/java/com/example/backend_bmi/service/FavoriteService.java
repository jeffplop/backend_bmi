package com.example.backend_bmi.service;

import com.example.backend_bmi.model.Favorite;
import com.example.backend_bmi.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<Favorite> obtenerFavoritosDeUsuario(Integer userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public Favorite agregarFavorito(Integer userId, Integer postId) {
        if (!favoriteRepository.existsByUserIdAndPostId(userId, postId)) {
            return favoriteRepository.save(new Favorite(null, userId, postId));
        }
        return null; 
    }

    @Transactional
    public void eliminarFavorito(Integer userId, Integer postId) {
        favoriteRepository.deleteByUserIdAndPostId(userId, postId);
    }
}