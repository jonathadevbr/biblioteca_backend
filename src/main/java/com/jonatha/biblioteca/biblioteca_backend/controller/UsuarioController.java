package com.jonatha.biblioteca.biblioteca_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario.UsuarioCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.UsuarioResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/v2/api/usuario")
@Tag(name = "Usuário", description = "Endpoints para gerenciamento de usuários no sistema")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Busca todos os usuários do sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuários listados com sucesso."),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioResponseDTO> getAllUsuarioController(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return usuarioService.getAllUsuarioService(PageRequest.of(page, size));
    }

    @Operation(summary = "Cria um usuário novo no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Usuário criado com sucesso."),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos fornecidos na requisição.",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
            responseCode = "409",
            description = "CPF ou e-mail já cadastrado.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CPF já cadastrado."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO createUsuarioController(@Valid @RequestBody UsuarioCreateRequestDTO request) {
        return usuarioService.createUsuarioService(request);
    }

    @Operation(summary = "Buscar um usuário por ID no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuário encontrado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Usuário não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO getUsuarioController(@PathVariable UUID id) {
        return usuarioService.getUsuarioService(id);
    }

    @Operation(summary = "Atualizar um usuário já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuário atualizado com sucesso."),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos fornecidos na requisição.",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Usuário não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "409",
            description = "CPF ou e-mail já cadastrado por outro usuário.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CPF já cadastrado."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO updateUsuarioController(@PathVariable UUID id, @Valid @RequestBody UsuarioUpdateRequestDTO  request) {
        return usuarioService.updateUsuarioService(id, request);
    }

    @Operation(summary = "Deletar um usuário já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Usuário deletado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Usuário não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuarioController(@PathVariable UUID id) {
        usuarioService.deleteUsuarioService(id);
    }
}