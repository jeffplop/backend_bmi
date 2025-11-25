package com.example.backend_bmi.service;

import com.example.backend_bmi.model.Category;
import com.example.backend_bmi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> obtenerTodas() {
        return categoryRepository.findAll();
    }

    public Category guardar(Category category) {
        return categoryRepository.save(category);
    }

    public void borrarTodo() {
        categoryRepository.deleteAll();
    }
}