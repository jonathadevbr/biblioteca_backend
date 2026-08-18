package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.UUID;


public record UsuarioResponseDTO(
    UUID id,
    String nome,
    String cpf,
    String email,
    String celular
) { }
