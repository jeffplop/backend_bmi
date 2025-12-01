package com.example.backend_bmi.controller;

import com.example.backend_bmi.model.Category;
import com.example.backend_bmi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.actualizar(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}