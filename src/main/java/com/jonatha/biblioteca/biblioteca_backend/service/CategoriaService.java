package com.jonatha.biblioteca.biblioteca_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jonatha.biblioteca.biblioteca_backend.dto.response.CategoriaResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Page<CategoriaResponseDTO> getAllCategoriaService(Pageable pageable) {
        return repository.findAll(pageable).map(CategoriaResponseDTO::new);
    }
}
