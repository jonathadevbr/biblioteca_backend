package com.jonatha.biblioteca.biblioteca_backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.UsuarioResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.ConflictException;
import com.jonatha.biblioteca.biblioteca_backend.exception.NotFoundException;
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

    public UsuarioResponseDTO createUsuarioService(UsuarioCreateRequestDTO request) {
        String cpfLimpo = request.cpf() != null ? request.cpf().replaceAll("\\D", "") : null;

        if (repository.existsByCpf(cpfLimpo)) {
            throw new ConflictException("CPF já cadastrado.");
        }

        if (repository.existsByEmail(request.email())) {
            throw new ConflictException("Email já cadastrado.");
        }

        Usuario usuario = request.createUsuario();

        usuario.setNome(tratarNome(request.nome()));
        usuario.setCpf(cpfLimpo);

        usuario = repository.save(usuario);
        return new UsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO getUsuarioService(UUID id) {
        Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado no sistema."));

        return new UsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO updateUsuarioService(UUID id, UsuarioUpdateRequestDTO request) {
        Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado no sistema."));

        if (repository.existsByEmailAndIdNot(request.email(), id)) {
            throw new ConflictException("Email já cadastrado.");
        }

        if (request.nome() != null) {
            usuario.setNome(tratarNome(request.nome()));
        }

        if (request.celular() != null) {
            usuario.setCelular(request.celular());
        }

        if (request.email() != null) {
            usuario.setEmail(request.email());
        }

        usuario = repository.save(usuario);

        return new UsuarioResponseDTO(usuario);
    }

    public void deleteUsuarioService(UUID id) {
        Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado no sistema."));

        repository.delete(usuario);
    }

    private String tratarNome(String nome) {
        if (nome == null) {
            return null;
        }
        return nome.trim().toUpperCase();
    }
}