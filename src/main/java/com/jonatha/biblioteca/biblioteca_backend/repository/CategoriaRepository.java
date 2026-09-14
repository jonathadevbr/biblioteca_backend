package com.jonatha.biblioteca.biblioteca_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsByDescricaoIgnoreCase(String descricao);
    boolean existsByNomeIgnoreCaseAndIdNot(String nome, UUID id);
    boolean existsByDescricaoIgnoreCaseAndIdNot(String descricao, UUID id);
}
