package com.example.backend_bmi.service;

import com.example.backend_bmi.exception.ResourceNotFoundException;
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

    public Category actualizar(Integer id, Category categoryDetails) {
        return categoryRepository.findById(id).map(category -> {
            category.setNombre(categoryDetails.getNombre());
            category.setDescripcion(categoryDetails.getDescripcion());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
    }

    public boolean eliminar(Integer id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Categoria no encontrada con ID: " + id);
        }
    }

    public void borrarTodo() {
        categoryRepository.deleteAll();
    }
}