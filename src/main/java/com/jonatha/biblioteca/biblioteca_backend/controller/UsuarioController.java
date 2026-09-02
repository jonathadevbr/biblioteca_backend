package com.jonatha.biblioteca.biblioteca_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.UsuarioResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Endpoints para gerenciamento de usuários no sistema")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Busca todos os usuários do sistema.")
    @GetMapping
    public Page<UsuarioResponseDTO> getUsuarioPage(
        @Parameter(hidden = true) @PageableDefault(size = 10) Pageable pageable){
            return service.getUsuarioPage(pageable);
    }

    @Operation(summary = "Cria um usuário novo no sistema.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO create(@Valid @RequestBody UsuarioCreateRequestDTO request) {
        return service.create(request);
    }

    @Operation(summary = "Buscar um usuário por ID no sistema.")
    @GetMapping("/{id}")
    public UsuarioResponseDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Operation(summary = "Atualizar um usuário já registrado no sistema.")
    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUsuarioController(@PathVariable UUID id,
            @Valid @RequestBody UsuarioUpdateRequestDTO request) {
        return service.update(id, request);
    }

    @Operation(summary = "Deletar um usuário já registrado no sistema.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}