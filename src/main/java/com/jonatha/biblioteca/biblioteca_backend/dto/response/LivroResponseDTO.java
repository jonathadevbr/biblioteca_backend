package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.List;
import java.util.UUID;

public record LivroResponseDTO(
    UUID id,
    String titulo,
    List<AutorResponseDTO> autores,
    CategoriaResponseDTO categoria,
    String isbn,
    Integer anoPublicacao,
    String editora,
    Integer quantidade
) { }