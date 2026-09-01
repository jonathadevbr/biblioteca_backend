package com.jonatha.biblioteca.biblioteca_backend.service;

import java.util.UUID;

import com.jonatha.biblioteca.biblioteca_backend.exception.ConflictException;
import com.jonatha.biblioteca.biblioteca_backend.repository.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.AutorResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.NotFoundException;
import com.jonatha.biblioteca.biblioteca_backend.mapper.AutorMapper;
import com.jonatha.biblioteca.biblioteca_backend.model.Autor;
import com.jonatha.biblioteca.biblioteca_backend.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository repository;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository repository, LivroRepository livroRepository) {
        this.repository = repository;
        this.livroRepository = livroRepository;
    }

    @Transactional(readOnly = true)
    public Page<AutorResponseDTO> getAutorPage(Pageable pageable) {
        return repository.findAll(pageable).map(AutorMapper::toDTOAutor);
    }

    @Transactional
    public AutorResponseDTO create(AutorCreateRequestDTO request) {
        Autor autor = AutorMapper.toEntityAutor(request);

        autor.setNome(tratarTexto(autor.getNome()));
        autor.setNacionalidade(tratarTexto(autor.getNacionalidade()));

        autor = repository.save(autor);
        return AutorMapper.toDTOAutor(autor);
    }

    @Transactional(readOnly = true)
    public AutorResponseDTO findById(UUID id) {
        Autor autor = buscarAutorPorId(id);

        return AutorMapper.toDTOAutor(autor);
    }

    @Transactional
    public AutorResponseDTO update(UUID id, AutorUpdateRequestDTO request) {
        Autor autor = buscarAutorPorId(id);

        if (request.nome() != null) {
            autor.setNome(tratarTexto(request.nome()));
        }

        if (request.nacionalidade() != null) {
            autor.setNacionalidade(tratarTexto(request.nacionalidade()));
        }

        autor = repository.save(autor);
        return AutorMapper.toDTOAutor(autor);
    }

    @Transactional
    public void delete(UUID id) {
        Autor autor = buscarAutorPorId(id);

        if (livroRepository.existsByAutoresId(id)) {
            throw new ConflictException("Não é possível excluir o autor pois há livros vinculados a ele.");
        }

        repository.delete(autor);
    }

    private Autor buscarAutorPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado no sistema."));
    }

    private String tratarTexto(String texto) {
        if (texto == null)
            return null;
        return texto.trim().toUpperCase();
    }
}
