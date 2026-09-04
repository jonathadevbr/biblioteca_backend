package com.jonatha.biblioteca.biblioteca_backend.controller;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.livro.LivroCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.livro.LivroUpdateRequestDTO;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.jonatha.biblioteca.biblioteca_backend.dto.response.LivroResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/livro")
@Tag(name = "Livro", description = "Endpoints para gerenciamento de livros no sistema.")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @Operation(summary = "Buscar todos os livros do sistema.")
    @GetMapping
    public Page<LivroResponseDTO> getPage(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
            return service.getPage(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO create(@Valid @RequestBody LivroCreateRequestDTO request) {
        return service.create(request);
    }

    @Operation(summary = "Buscar um livro por ID no sistema.")
    @GetMapping("/{id}")
    public LivroResponseDTO findById(@Valid @PathVariable UUID id) {
        return service.findById(id);
    }
    
    @Operation(summary = "Atualizar um livro já registrado no sistema.")
    @PutMapping("/{id}")
    public LivroResponseDTO update(@PathVariable UUID id, @RequestBody LivroUpdateRequestDTO request) {        
        return service.update(id, request);
    }

    @Operation(summary = "Deletar um livro já registrado no sistema.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
    
}
