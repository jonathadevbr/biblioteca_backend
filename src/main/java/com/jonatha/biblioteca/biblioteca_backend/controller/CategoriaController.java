package com.jonatha.biblioteca.biblioteca_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.CategoriaResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/v2/biblioteca/categoria")
@Tag(name = "Categoria", description = "Endpoints para gerenciamento de categorias do sistema")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // GET ALL
    @Operation(summary = "Busca todos as categorias do sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Categorias listados com sucesso."),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoriaResponseDTO> getAllCategoriaController(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return categoriaService.getAllCategoriaService(PageRequest.of(page, size));
    }

    // POST
    @Operation(summary = "Cria uma categoria novo no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Categoria criado com sucesso."),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos fornecidos na requisição.",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDTO createCategoriaController(@Valid @RequestBody CategoriaCreateRequestDTO request) {
        return categoriaService.createCategoriaService(request);
    }
    
    // GET
    @Operation(summary = "Buscar uma categoria por ID no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Categoria encontrado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Categoria não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Categoria não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaResponseDTO getCategoria(@PathVariable UUID id) {
        return categoriaService.getCategoriaService(id);
    }
    
    // PUT
    @Operation(summary = "Atualizar uma categoria já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Categoria atualizado com sucesso."),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos fornecidos na requisição.",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
            responseCode = "404",
            description = "Autor não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Usuário não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    public CategoriaResponseDTO updateCategoriaController(@PathVariable UUID id, @Valid @RequestBody CategoriaUpdateRequestDTO request) {        
        return categoriaService.updateCategoriaService(id, request);
    }
}
