package com.jonatha.biblioteca.biblioteca_backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.CategoriaResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.ConflictException;
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

    public CategoriaResponseDTO createCategoriaService(CategoriaCreateRequestDTO request) {
        String nomeTratado = tratarNome(request.nome());
        String descricaoTratada = tratarDescricao(request.descricao());

        if (repository.existsByNome(nomeTratado)) {
            throw new ConflictException("Nome de categoria já cadastrado.");
        }

        if (repository.existsByDescricao(descricaoTratada)) {
            throw new ConflictException("Descrição da categoria já cadastrado.");
        }

        Categoria categoria = request.createCategoria();
        categoria.setNome(nomeTratado);
        categoria.setDescricao(descricaoTratada);

        categoria = repository.save(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    public CategoriaResponseDTO getCategoriaService(UUID id) {
        Categoria categoria = buscarCategoriaPorId(id);

            return new CategoriaResponseDTO(categoria);
    }

    public CategoriaResponseDTO updateCategoriaService(UUID id, CategoriaUpdateRequestDTO request) {
        Categoria categoria = buscarCategoriaPorId(id);

        String nomeTratado = tratarNome(request.nome());
        String descricaoTratada = tratarDescricao(request.descricao());

        if (nomeTratado != null && repository.existsByNomeAndIdNot(nomeTratado, id)) {
            throw new ConflictException("Nome de categoria já cadastrado.");
        }

        if (descricaoTratada != null && repository.existsByDescricaoAndIdNot(descricaoTratada, id)) {
            throw new ConflictException("Descrição da categoria já cadastrado.");
        }

        if (nomeTratado != null) {
            categoria.setNome(nomeTratado);
        }

        if (descricaoTratada != null) {
            categoria.setDescricao(descricaoTratada);
        }

        categoria = repository.save(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    public void deleteCategoriaService(UUID id) {
        Categoria categoria = buscarCategoriaPorId(id);

        repository.delete(categoria);
    }

    private Categoria buscarCategoriaPorId(UUID id) {
        return repository.findById(id) 
            .orElseThrow(() -> new NotFoundException("Categoria não encontrada no sistema."));
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
