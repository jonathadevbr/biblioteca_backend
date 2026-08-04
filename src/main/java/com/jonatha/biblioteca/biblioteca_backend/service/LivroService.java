package com.jonatha.biblioteca.biblioteca_backend.service;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.livro.LivroCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.livro.LivroUpdateRequestDTO;
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

    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> getAllLivroService(Pageable pageable) {
        return repository.findAll(pageable).map(LivroResponseDTO::new);
    }

    @Transactional
    public LivroResponseDTO createLivroService(LivroCreateRequestDTO request) {
        String isbnLimpo = request.isbn() != null ? request.isbn().replaceAll("\\D", "") : null;

        if (repository.existsByTitulo(request.titulo())) {
            throw new ConflictException("Título já cadastrado.");
        }

        if (repository.existsByIsbn(isbnLimpo)) {
            throw new ConflictException("ISBN já cadastrado.");
        }

        Set<Autor> autores = new HashSet<>(autorRepository.findAllById(request.idsAutores()));

        if (autores.size() != new HashSet<>(request.idsAutores()).size()) {
            throw new NotFoundException("Um ou mais autores não foram encontrados.");
        }

        Categoria categoria = categoriaRepository.findById(request.idCategoria())
            .orElseThrow(() -> new NotFoundException("Categoria não encontrada."));

        Livro livro = request.createLivro(autores, categoria);

        livro.setTitulo(tratarTitulo(request.titulo()));
        livro.setIsbn(isbnLimpo);

        livro = repository.save(livro);
        return new LivroResponseDTO(livro);
    }

    @Transactional(readOnly = true)
    public LivroResponseDTO getLivroService(UUID id) {
        Livro livro = buscarLivroPorId(id);

        return new LivroResponseDTO(livro);
    }

    @Transactional
    public LivroResponseDTO updateLivroService(UUID id, LivroUpdateRequestDTO request) {
        Livro livro = buscarLivroPorId(id);

        if (request.titulo() != null) {
            livro.setTitulo(tratarTitulo(request.titulo()));
        }

        if (request.idsAutores() != null) {
            Set<Autor> autores = new HashSet<>(autorRepository.findAllById(request.idsAutores()));

            if (autores.size() != new HashSet<>(request.idsAutores()).size()) {
                throw new NotFoundException("Um ou mais autores não foram encontrados.");
            }

            livro.setAutores(autores);
        }

        if (request.idCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(request.idCategoria())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada."));
            livro.setCategoria(categoria);
        }

        if (request.anoPublicacao() != null) {
            livro.setAnoPublicacao(request.anoPublicacao());
        }

        if (request.editora() != null) {
            livro.setEditora(tratarEditora(request.editora()));
        }

        if (request.quantidade() != null) {
            livro.setQuantidade(request.quantidade());
        }

        livro = repository.save(livro);
        return new LivroResponseDTO(livro);
    }

    @Transactional
    public void deleteLivroService(UUID id) {
        Livro livro = buscarLivroPorId(id);

        repository.delete(livro);
    }

    private Livro buscarLivroPorId(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Livro não encontrado no sistema."));
    }

    private String tratarTitulo(String titulo) {
        if (titulo == null) return null;
        return titulo.trim().toUpperCase();
    }

    private String tratarEditora(String editora) {
        if (editora == null) return null;
        return editora.trim().toUpperCase();
    }
}
