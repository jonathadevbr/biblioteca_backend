package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.UUID;

public record AutorResponseDTO(
    UUID id,
    String nome,
    String nacionalidade
) { }
