package com.example.backend_bmi.controller;

import com.example.backend_bmi.model.Category;
import com.example.backend_bmi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.obtenerTodas();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.guardar(category);
    }
}