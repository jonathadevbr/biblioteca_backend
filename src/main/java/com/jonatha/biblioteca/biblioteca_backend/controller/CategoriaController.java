package com.jonatha.biblioteca.biblioteca_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.response.CategoriaResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


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
    
}
