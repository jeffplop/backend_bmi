package com.example.backend_bmi.controller;

import com.example.backend_bmi.model.Favorite;
import com.example.backend_bmi.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable Integer userId) {
        return favoriteService.obtenerFavoritosDeUsuario(userId);
    }

    @PostMapping("/add")
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        return favoriteService.agregarFavorito(favorite.getUserId(), favorite.getPostId());
    }

    @DeleteMapping("/remove/{userId}/{postId}")
    public void removeFavorite(@PathVariable Integer userId, @PathVariable Integer postId) {
        favoriteService.eliminarFavorito(userId, postId);
    }
}