package com.jonatha.biblioteca.biblioteca_backend.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.AutorResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.AutorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v2/biblioteca/autor")
@Tag(name = "Autor", description = "Endpoints para gerenciamento de autores no sistema")
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }
    
    // GET ALL
    @Operation(summary = "Busca todos os autores do sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Autores listados com sucesso."),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AutorResponseDTO> getAllAutorController(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return autorService.getAllAutorService(PageRequest.of(page, size));
    }

    // POST
    @Operation(summary = "Cria um autor novo no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Autor criado com sucesso."),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos fornecidos na requisição.",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutorResponseDTO createAutorController(@Valid @RequestBody AutorCreateRequestDTO request) {
        return autorService.createAutorService(request);
    }

    // GET
    @Operation(summary = "Buscar um autor por ID no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Autor encontrado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Autor não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Autor não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AutorResponseDTO getUsuarioController(@PathVariable UUID id) {
        return autorService.getAutorService(id);
    }

    // PUT
    @Operation(summary = "Atualizar um autor já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Autor atualizado com sucesso."),
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
    @ResponseStatus(HttpStatus.OK)
    public AutorResponseDTO updateAutorController(@PathVariable UUID id, @Valid @RequestBody AutorUpdateRequestDTO  request) {
        return autorService.updateAutorService(id, request);
    }

    // DELETE
    @Operation(summary = "Deletar um autor já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Autor deletado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Autor não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Usuário não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAutorController(@PathVariable UUID id) {
        autorService.deleteAutorService(id);
    }
}