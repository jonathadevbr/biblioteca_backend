package com.jonatha.biblioteca.biblioteca_backend.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.emprestimo.EmprestimoCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.emprestimo.EmprestimoUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.EmprestimoResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.EmprestimoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v2/biblioteca/emprestimo")
@Tag(name = "Emprestimo", description = "Endpoints para gerenciamento de emprestimos no sistema.")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    // GET ALL
    @Operation(summary = "Buscar todos os emprestimos do sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Emprestimos listados com sucesso."),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    public Page<EmprestimoResponseDTO> getAllEmprestimoController(Pageable pageable) {
        return emprestimoService.getAllEmprestimoService(pageable);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoResponseDTO createEmprestimoController(@Valid @RequestBody EmprestimoCreateRequestDTO request) {
        return emprestimoService.createEmprestimoSerivce(request);
    }

    // GET {ID}
    @Operation(summary = "Buscar um empréstimo por ID no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Empréstimo encontrado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Empréstimo não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Empréstimo não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    public EmprestimoResponseDTO getEmprestimoController(@Valid @PathVariable UUID id) {
        return emprestimoService.getEmprestimoService(id);
    }
    
    // PUT
    @Operation(summary = "Atualizar um empréstimo já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Empréstimo atualizado com sucesso."),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos fornecidos na requisição.",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
            responseCode = "404",
            description = "Empréstimo não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Empréstimo não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    public EmprestimoResponseDTO updateEmprestimoController(@PathVariable UUID id, @RequestBody EmprestimoUpdateRequestDTO request) {        
        return emprestimoService.updateEmprestimoService(id, request);
    }

    // DELETE
    @Operation(summary = "Deletar um empréstimo já registrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Empréstimo deletado com sucesso."),
        @ApiResponse(
            responseCode = "404",
            description = "Empréstimo não encontrado na base de dados.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Empréstimo não encontrado no sistema."))),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno de servidor.",
            content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmprestimoController(@PathVariable UUID id) {
        emprestimoService.deleteEmprestimoService(id);
    }
}
