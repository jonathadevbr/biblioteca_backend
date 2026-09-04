package com.jonatha.biblioteca.biblioteca_backend.controller;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.AutorResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.AutorService;

import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autor")
@Tag(name = "Autor", description = "Endpoints para gerenciamento de autores no sistema")
public class AutorController {
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @Operation(summary = "Busca todos os autores do sistema.")
    @GetMapping
    public Page<AutorResponseDTO> getPage(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
            return service.getPage(pageable);
    }

    @Operation(summary = "Cria um autor novo no sistema.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutorResponseDTO create(@Valid @RequestBody AutorCreateRequestDTO request) {
        return service.create(request);
    }

    @Operation(summary = "Buscar um autor por ID no sistema.")
    @GetMapping("/{id}")
    public AutorResponseDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Operation(summary = "Atualizar um autor já registrado no sistema.")
    @PutMapping("/{id}")
    public AutorResponseDTO update(@PathVariable UUID id, @Valid @RequestBody AutorUpdateRequestDTO request) {
        return service.update(id, request);
    }

    @Operation(summary = "Deletar um autor já registrado no sistema.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}