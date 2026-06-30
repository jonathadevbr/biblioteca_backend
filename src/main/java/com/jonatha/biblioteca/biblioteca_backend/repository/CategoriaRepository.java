package com.jonatha.biblioteca.biblioteca_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    boolean existsByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, UUID id);

    boolean existsByDescricao(String descricao);

    boolean existsByDescricaoAndIdNot(String descricao, UUID id);
}
