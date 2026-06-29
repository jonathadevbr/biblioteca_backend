package com.jonatha.biblioteca.biblioteca_backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.AutorResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.NotFoundException;
import com.jonatha.biblioteca.biblioteca_backend.model.Autor;
import com.jonatha.biblioteca.biblioteca_backend.repository.AutorRepository;

@Service
public class AutorService {
    
    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Page<AutorResponseDTO> getAllAutorService(Pageable pageable) {
        return repository.findAll(pageable).map(AutorResponseDTO::new);
    }

    public AutorResponseDTO createAutorService(AutorCreateRequestDTO request) {
        Autor autor = request.createAutor();
        
        autor.setNome(tratarNome(request.nome()));
        autor.setNacionalidade(tratarNacionalidade(request.nacionalidade()));

        autor = repository.save(autor);
        return new AutorResponseDTO(autor);
    }

    public AutorResponseDTO getAutorService(UUID id) {
        Autor autor = buscarAutorPorId(id);

        return new AutorResponseDTO(autor);
    }

    public AutorResponseDTO updateAutorService(UUID id, AutorUpdateRequestDTO request) {
        Autor autor = buscarAutorPorId(id);
    
        if (request.nome() != null) {
            autor.setNome(tratarNome(request.nome()));
        }

        if (request.nacionalidade() != null) {
            autor.setNacionalidade(tratarNacionalidade(request.nacionalidade()));
        }

        autor = repository.save(autor);
        
        return new AutorResponseDTO(autor);
    }

    public void deleteAutorService(UUID id) {
        Autor autor = buscarAutorPorId(id);
        
        repository.delete(autor);
    }

    private Autor buscarAutorPorId(UUID id) {
        return repository.findById(id) 
            .orElseThrow(() -> new NotFoundException("Autor não encontrada no sistema."));
    }

    private String tratarNome(String nome) {
        if (nome == null) return null;
        return nome.trim().toUpperCase();
    }

    private String tratarNacionalidade(String nacionalidade) {
        if (nacionalidade == null) return null;
        return nacionalidade.trim().toUpperCase();
    }

}
