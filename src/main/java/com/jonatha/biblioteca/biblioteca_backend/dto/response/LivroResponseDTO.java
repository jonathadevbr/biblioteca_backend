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
    public LivroResponseDTO(Livro entity) {
        this(
            entity.getId(),
            entity.getTitulo(),
                entity.getAutores().stream()
                        .map(autor -> autor.getId())
                        .collect(Collectors.toList()),
            entity.getCategoria().getDescricao(),
            entity.getIsbn(),
            entity.getAnoPublicacao(),
            entity.getEditora(),
            entity.getQuantidade()
        );
    }
}
