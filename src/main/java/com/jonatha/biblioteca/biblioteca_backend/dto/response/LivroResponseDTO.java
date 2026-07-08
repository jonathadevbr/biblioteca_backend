package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jonatha.biblioteca.biblioteca_backend.model.Livro;

public record LivroResponseDTO(
    UUID id,
    String titulo,
    List<UUID> idsAutores,
    String descricaoCategoria,
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
                        .map(autor -> autor.getId())
                        .collect(Collectors.toList()),
            livro.getCategoria().getDescricao(),
            livro.getIsbn(),
            livro.getAnoPublicacao(),
            livro.getEditora(),
            livro.getQuantidade()
        );
    }
}
