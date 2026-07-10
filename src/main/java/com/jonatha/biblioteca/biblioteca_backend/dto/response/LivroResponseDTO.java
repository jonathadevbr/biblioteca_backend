package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jonatha.biblioteca.biblioteca_backend.model.Livro;

public record LivroResponseDTO(
    UUID id,
    String titulo,
    List<AutorResponseDTO> autores,
    CategoriaResponseDTO categoria,
    String isbn,
    Integer anoPublicacao,
    String editora,
    Integer quantidade
) {
    public LivroResponseDTO(Livro livro) {
        this(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutores().stream()
                .map(AutorResponseDTO::new)
                .collect(Collectors.toList()),
            new CategoriaResponseDTO(livro.getCategoria()),
            livro.getIsbn(),
            livro.getAnoPublicacao(),
            livro.getEditora(),
            livro.getQuantidade()
        );
    }
}