package com.example.backend_bmi.repository;

import com.example.backend_bmi.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(Integer userId);
    void deleteByUserIdAndPostId(Integer userId, Integer postId);
    boolean existsByUserIdAndPostId(Integer userId, Integer postId);
}