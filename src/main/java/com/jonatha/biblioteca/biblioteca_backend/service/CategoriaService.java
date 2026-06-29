package com.jonatha.biblioteca.biblioteca_backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.CategoriaResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.NotFoundException;
import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;
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

    public CategoriaResponseDTO getCategoriaService(UUID id) {
        Categoria categoria = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Categoria não encontrada no sistema."));

            return new CategoriaResponseDTO(categoria);
    }

    public CategoriaResponseDTO createCategoriaService(CategoriaCreateRequestDTO request) {
        Categoria categoria = request.createCategoria();

        categoria.setNome(tratarNome(request.nome()));
        categoria.setDescricao(tratarDescricao(request.descricao()));

        categoria = repository.save(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    private String tratarNome(String nome) {
        if (nome == null) return null;
        return nome.trim().toUpperCase();
    }

    private String tratarDescricao(String descricao) {
        if (descricao == null) return null;
        return descricao.trim().toUpperCase();
    }
}
