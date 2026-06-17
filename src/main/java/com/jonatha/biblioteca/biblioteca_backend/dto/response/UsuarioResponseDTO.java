package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.util.UUID;

import com.jonatha.biblioteca.biblioteca_backend.model.Usuario;

public record UsuarioResponseDTO(
    UUID id,
    String nome,
    String cpf,
    String email,
    String celular
) {
    public UsuarioResponseDTO(Usuario entity) {
        this(
            entity.getId(),
            entity.getNome(),
            entity.getCpf(),
            entity.getEmail(),
            entity.getCelular()
        );
    }
}
