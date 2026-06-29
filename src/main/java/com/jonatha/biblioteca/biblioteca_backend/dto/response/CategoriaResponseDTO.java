package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.UUID;

import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;

public record CategoriaResponseDTO(
    UUID id,
    String nome,
    String descricao
) {
    public CategoriaResponseDTO(Categoria categoria) {
        this(
            categoria.getId(),
            categoria.getNome(),
            categoria.getDescricao()
        );
    }
}
