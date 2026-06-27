package com.jonatha.biblioteca.biblioteca_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatha.biblioteca.biblioteca_backend.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, UUID>{
    
}
