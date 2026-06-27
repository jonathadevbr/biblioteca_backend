package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.UUID;

import com.jonatha.biblioteca.biblioteca_backend.model.Autor;

public record AutorResponseDTO(
    UUID id,
    String nome,
    String nacionalidade
) {
    public AutorResponseDTO(Autor autor) {
        this(
            autor.getId(),
            autor.getNome(),
            autor.getNacionalidade()
        );
    }
}
