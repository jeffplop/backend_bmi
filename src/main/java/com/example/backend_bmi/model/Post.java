package com.example.backend_bmi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String title;
    @Column(length = 1000) // Aumentamos el largo por si la descripción es larga
    private String body;
    private String pdfUrl;
    private String version;
    private String fecha;
    private String fabricante;
    private Boolean isPremium;
    
    // USAR SOLO ESTO (INTEGER), NO 'private Category category;'
    private Integer categoryId; 
}