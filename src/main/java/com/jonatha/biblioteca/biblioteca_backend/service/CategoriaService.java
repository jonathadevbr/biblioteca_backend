package com.jonatha.biblioteca.biblioteca_backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.CategoriaResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.ConflictException;
import com.jonatha.biblioteca.biblioteca_backend.exception.NotFoundException;
import com.jonatha.biblioteca.biblioteca_backend.mapper.CategoriaMapper;
import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;
import com.jonatha.biblioteca.biblioteca_backend.repository.CategoriaRepository;
import com.jonatha.biblioteca.biblioteca_backend.repository.LivroRepository;
import com.jonatha.biblioteca.biblioteca_backend.utils.TextUtils;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    private final LivroRepository livroRepository;

    public CategoriaService(CategoriaRepository repository, LivroRepository livroRepository) {
        this.repository = repository;
        this.livroRepository = livroRepository;
    }

    @Transactional(readOnly = true)
    public Page<CategoriaResponseDTO> getPage(Pageable pageable) {
        return repository.findAll(pageable).map(CategoriaMapper::toDTOCategoria);
    }

    @Transactional
    public CategoriaResponseDTO create(CategoriaCreateRequestDTO request) {
        Categoria categoria = CategoriaMapper.toEntityCategoria(request);

        String nomeTratado = TextUtils.tratarTexto(categoria.getNome());
        String descricaoTratada = TextUtils.tratarTexto(categoria.getDescricao());

        if (repository.existsByNome(nomeTratado)) {
            throw new ConflictException("Nome de categoria já cadastrado.");
        }

        if (repository.existsByDescricao(descricaoTratada)) {
            throw new ConflictException("Descrição da categoria já cadastrada.");
        }

        categoria.setNome(nomeTratado);
        categoria.setDescricao(descricaoTratada);

        categoria = repository.save(categoria);
        return CategoriaMapper.toDTOCategoria(categoria);
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDTO findById(UUID id) {
        Categoria categoria = buscarCategoriaPorId(id);

        return CategoriaMapper.toDTOCategoria(categoria);
    }

    @Transactional
    public CategoriaResponseDTO update(UUID id, CategoriaUpdateRequestDTO request) {
        Categoria categoria = buscarCategoriaPorId(id);

        String nomeTratado = TextUtils.tratarTexto(request.nome());
        String descricaoTratada = TextUtils.tratarTexto(request.descricao());

        if (nomeTratado != null && repository.existsByNomeAndIdNot(nomeTratado, id)) {
            throw new ConflictException("Nome de categoria já cadastrado.");
        }

        if (descricaoTratada != null && repository.existsByDescricaoAndIdNot(descricaoTratada, id)) {
            throw new ConflictException("Descrição da categoria já cadastrada.");
        }

        if (nomeTratado != null) {
            categoria.setNome(nomeTratado);
        }

        if (descricaoTratada != null) {
            categoria.setDescricao(descricaoTratada);
        }

        categoria = repository.save(categoria);
        return CategoriaMapper.toDTOCategoria(categoria);
    }

    @Transactional
    public void delete(UUID id) {
        Categoria categoria = buscarCategoriaPorId(id);

        if (livroRepository.existsByCategoriaId(id)) {
            throw new ConflictException("Não é possível excluir o categorias pois há livros vinculados a ela.");
        }

        repository.delete(categoria);
    }

    private Categoria buscarCategoriaPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada no sistema."));
    }
}
