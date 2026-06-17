package com.jonatha.biblioteca.biblioteca_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.UsuarioRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.UsuarioResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.service.UsuarioService;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/v2/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioResponseDTO> getAllUsuarioController(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return usuarioService.getAllUsuarioService(PageRequest.of(page, size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO createUsuarioController(@Valid @RequestBody UsuarioRequestDTO request) {
        return usuarioService.createUsuarioService(request);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO getUsuarioController(@RequestParam UUID id) {
        return usuarioService.getUsuarioService(id);
    }
    
    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUsuarioController(@PathVariable UUID id, @Valid @RequestBody UsuarioRequestDTO request) {
        return usuarioService.updateUsuarioService(id, request);
    }
    
}
