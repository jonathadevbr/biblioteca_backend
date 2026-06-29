package com.jonatha.biblioteca.biblioteca_backend.service;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.livro.LivroCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.LivroResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.ConflictException;
import com.jonatha.biblioteca.biblioteca_backend.exception.NotFoundException;
import com.jonatha.biblioteca.biblioteca_backend.model.Autor;
import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;
import com.jonatha.biblioteca.biblioteca_backend.model.Livro;
import com.jonatha.biblioteca.biblioteca_backend.repository.AutorRepository;
import com.jonatha.biblioteca.biblioteca_backend.repository.CategoriaRepository;
import com.jonatha.biblioteca.biblioteca_backend.repository.LivroRepository;


@Service
public class LivroService {

    private final LivroRepository repository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public LivroService(
        LivroRepository repository,
        AutorRepository autorRepository,
        CategoriaRepository categoriaRepository
    ) {
        this.repository = repository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Page<LivroResponseDTO> getAllLivroService(Pageable pageable) {
        return repository.findAll(pageable).map(LivroResponseDTO::new);
    }

    public LivroResponseDTO createLivroService(LivroCreateRequestDTO request) {
        String isbnLimpo = request.isbn() != null ? request.isbn().replaceAll("\\D", "") : null;

        if (repository.existsByTitulo(request.titulo())) {
            throw new ConflictException("Título já cadastrado.");
        }

        if (repository.existsByIsbn(isbnLimpo)) {
            throw new ConflictException("ISBN já cadastrado.");
        }

        Autor autor = autorRepository.findById(request.idAutor()) 
            .orElseThrow(() -> new NotFoundException("Autor não encontrado."));

        Categoria categoria = categoriaRepository.findById(request.idCategoria())
            .orElseThrow(() -> new NotFoundException("Categoria não encontrada."));

        Livro livro = request.createLivro(autor, categoria);

        livro.setTitulo(tratarTitulo(request.titulo()));
        livro.setIsbn(isbnLimpo);

        livro = repository.save(livro);
        return new LivroResponseDTO(livro);
    }

    private Livro buscarLivroPorId(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Livro não encontrado no sistema."));
    }

    private String tratarTitulo(String titulo) {
        if (titulo == null) return null;
        return titulo.trim().toUpperCase();
    }
}
