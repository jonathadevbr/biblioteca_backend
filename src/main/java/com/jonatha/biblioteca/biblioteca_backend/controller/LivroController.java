package com.jonatha.biblioteca.biblioteca_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.response.LivroResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/v2/api/livro")
@Tag(name = "Livro", description = "Endpoints para gerenciamento de livros no sistema.")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @Operation(summary = "Buscar todos os livros do sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Livros listados com sucesso."),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<LivroResponseDTO> getAllLivroController(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return livroService.getAllLivroService(PageRequest.of(page, size));
    }
    
}
