package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.UUID;


public record CategoriaResponseDTO(
    UUID id,
    String nome,
    String descricao
) { }
