package com.jonatha.biblioteca.biblioteca_backend.controller;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.livro.LivroCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.livro.LivroUpdateRequestDTO;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.jonatha.biblioteca.biblioteca_backend.dto.response.LivroResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/v2/biblioteca/livro")
@Tag(name = "Livro", description = "Endpoints para gerenciamento de livros no sistema.")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // GET ALL
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

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO createLivroController(@Valid @RequestBody LivroCreateRequestDTO request) {
        return livroService.createLivroService(request);
    }

    // GET
    @Operation(summary = "Buscar um livro por ID no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Livro encontrado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Livro não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Livro não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LivroResponseDTO getLivroController(@Valid @PathVariable UUID id) {
        return livroService.getLivroService(id);
    }
    
    // PUT
    @Operation(summary = "Atualizar um livro já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Livro atualizado com sucesso."),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos fornecidos na requisição.",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
            responseCode = "404",
            description = "Livro não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Livro não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    public LivroResponseDTO updateLivroController(@Valid @PathVariable UUID id, @Valid @RequestBody LivroUpdateRequestDTO request) {        
        return livroService.updateLivroService(id, request);
    }

    // DELETE
    @Operation(summary = "Deletar um livro já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Livro deletado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Livro não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Livro não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLivroController(@PathVariable UUID id) {
        livroService.deleteLivroService(id);
    }
    
}
