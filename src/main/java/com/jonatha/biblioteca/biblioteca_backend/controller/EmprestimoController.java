package com.jonatha.biblioteca.biblioteca_backend.controller;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/biblioteca/emprestimo")
@Tag(name = "Emprestimo", description = "Endpoints para gerenciamento de emprestimos no sistema.")
public class EmprestimoController {
    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @Operation(summary = "Buscar todos os emprestimos do sistema.")
    @GetMapping
    public Page<EmprestimoResponseDTO> getPage(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return service.getPage(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoResponseDTO create(@Valid @RequestBody EmprestimoCreateRequestDTO request) {
        return service.create(request);
    }

    @Operation(summary = "Buscar um empréstimo por ID no sistema.")
    @GetMapping("/{id}")
    public EmprestimoResponseDTO findById(@Valid @PathVariable UUID id) {
        return service.findById(id);
    }
    
    @Operation(summary = "Atualizar um empréstimo já registrado no sistema.")
    @PutMapping("/{id}")
    public EmprestimoResponseDTO update(@PathVariable UUID id, @RequestBody EmprestimoUpdateRequestDTO request) {        
        return service.update(id, request);
    }

    @Operation(summary = "Deletar um empréstimo já registrado no sistema.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
