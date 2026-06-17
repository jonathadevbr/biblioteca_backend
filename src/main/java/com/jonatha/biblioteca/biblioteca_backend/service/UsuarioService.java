package com.jonatha.biblioteca.biblioteca_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.UsuarioRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.UsuarioResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.model.Usuario;
import com.jonatha.biblioteca.biblioteca_backend.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Page<UsuarioResponseDTO> getAllUsuarioService(Pageable pageable) {
        return repository.findAll(pageable).map(UsuarioResponseDTO::new);
    }

    public UsuarioResponseDTO createUsuarioService(UsuarioRequestDTO request) {
        Usuario usuario = request.createUsuario();

        usuario = repository.save(usuario);
        return new UsuarioResponseDTO(usuario);
    }
}
