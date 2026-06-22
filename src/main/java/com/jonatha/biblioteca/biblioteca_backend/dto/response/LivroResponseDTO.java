package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.UUID;

import com.jonatha.biblioteca.biblioteca_backend.model.Livro;

public record LivroResponseDTO(
    UUID id,
    String titulo,
    String nomeAutor,
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
            entity.getAutor().getNome(),
            entity.getCategoria().getDescricao(),
            entity.getIsbn(),
            entity.getAnoPublicacao(),
            entity.getEditora(),
            entity.getQuantidade()
        );
    }
}
