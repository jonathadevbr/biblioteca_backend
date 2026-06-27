package com.jonatha.biblioteca.biblioteca_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatha.biblioteca.biblioteca_backend.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, UUID>{
    boolean existsByTitulo(String titulo);
    boolean existsByIsbn(String isbn);
    boolean existsByTituloAndIdNot(String titulo, UUID id);
    boolean existsByIsbnAndIdNot(String isbn, UUID id);
}
