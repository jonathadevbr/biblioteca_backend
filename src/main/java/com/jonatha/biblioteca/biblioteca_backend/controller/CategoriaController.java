package com.jonatha.biblioteca.biblioteca_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.CategoriaResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.CategoriaService;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/categoria")
@Tag(name = "Categoria", description = "Endpoints para gerenciamento de categorias do sistema")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @Operation(summary = "Busca todas as categorias do sistema.")
    @GetMapping
    public Page<CategoriaResponseDTO> getPage(
        @Parameter(hidden = true) @PageableDefault(size = 10) Pageable pageable) {
            return service.getPage(pageable);
    }

    @Operation(summary = "Cria uma categoria nova no sistema.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDTO create(@Valid @RequestBody CategoriaCreateRequestDTO request) {
        return service.create(request);
    }

    @Operation(summary = "Buscar uma categoria por ID no sistema.")
    @GetMapping("/{id}")
    public CategoriaResponseDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Operation(summary = "Atualizar uma categoria já registrada no sistema.")
    @PutMapping("/{id}")
    public CategoriaResponseDTO updateCategoriaController(@PathVariable UUID id, @Valid @RequestBody CategoriaUpdateRequestDTO request) {        
        return service.update(id, request);
    }

    @Operation(summary = "Deletar uma categoria já registrada no sistema.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}