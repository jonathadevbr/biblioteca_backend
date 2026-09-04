package com.jonatha.biblioteca.biblioteca_backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.UsuarioResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.ConflictException;
import com.jonatha.biblioteca.biblioteca_backend.exception.NotFoundException;
import com.jonatha.biblioteca.biblioteca_backend.mapper.UsuarioMapper;
import com.jonatha.biblioteca.biblioteca_backend.model.Usuario;
import com.jonatha.biblioteca.biblioteca_backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<UsuarioResponseDTO> getPage(Pageable pageable) {
        return repository.findAll(pageable).map(UsuarioMapper::toDTOUsuario);
    }

    @Transactional
    public UsuarioResponseDTO create(UsuarioCreateRequestDTO request) {
        String cpfLimpo = request.cpf() != null ? request.cpf().replaceAll("\\D", "") : null;
        String nomeTratado = tratarTexto(request.nome());

        if (repository.existsByCpf(cpfLimpo))
            throw new ConflictException("CPF já cadastrado.");
        if (repository.existsByEmail(request.email()))
            throw new ConflictException("Email já cadastrado.");

        Usuario usuario = UsuarioMapper.toEntityUsuario(request);

        usuario.setNome(nomeTratado);
        usuario.setCpf(cpfLimpo);

        usuario = repository.save(usuario);
        return UsuarioMapper.toDTOUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(UUID id) {
        Usuario usuario = buscarUsuarioPorId(id);

        return UsuarioMapper.toDTOUsuario(usuario);
    }

    @Transactional
    public UsuarioResponseDTO update(UUID id, UsuarioUpdateRequestDTO request) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (request.email() != null) {
            if (repository.existsByEmailAndIdNot(request.email(), id)) {
                throw new ConflictException("Email já cadastrado.");
            }
            usuario.setEmail(request.email());
        }

        if (request.nome() != null) usuario.setNome(tratarTexto(request.nome()));
        if (request.celular() != null) usuario.setCelular(request.celular());

        usuario = repository.save(usuario);

        return UsuarioMapper.toDTOUsuario(usuario);
    }

    @Transactional
    public void delete(UUID id) {
        Usuario usuario = buscarUsuarioPorId(id);

        repository.delete(usuario);
    }

    private Usuario buscarUsuarioPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado no sistema."));
    }

    private String tratarTexto(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.trim().toUpperCase();
    }
}