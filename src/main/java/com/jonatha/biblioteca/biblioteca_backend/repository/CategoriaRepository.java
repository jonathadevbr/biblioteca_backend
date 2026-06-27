package com.jonatha.biblioteca.biblioteca_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    
}
