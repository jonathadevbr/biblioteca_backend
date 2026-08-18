package com.jonatha.biblioteca.biblioteca_backend.mapper;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.UsuarioResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.model.Usuario;

public class UsuarioMapper {
    // ENTRADA de dados
    public static Usuario toEntityUsuario(UsuarioCreateRequestDTO request) {
        if (request == null) return null;
        
        Usuario usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setCpf(request.cpf());
        usuario.setEmail(request.email());
        usuario.setCelular(request.celular());
        return usuario;
    }

    // SAÍDA de dados
    public static UsuarioResponseDTO toDTOUsuario(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            usuario.getCelular()
        );
    }
}
