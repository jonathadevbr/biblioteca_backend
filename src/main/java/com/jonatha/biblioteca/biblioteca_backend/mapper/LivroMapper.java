package com.jonatha.biblioteca.biblioteca_backend.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.livro.LivroCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.LivroResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.model.Autor;
import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;
import com.jonatha.biblioteca.biblioteca_backend.model.Livro;

public class LivroMapper {

    // ENTRADA de dados
    public static Livro toEntityLivro(LivroCreateRequestDTO request, Set<Autor> autores, Categoria categoria) {
        if (request == null) return null;

        Livro livro = new Livro();
        livro.setTitulo(request.titulo());
        livro.setAutores(autores);
        livro.setCategoria(categoria);
        livro.setIsbn(request.isbn());
        livro.setAnoPublicacao(request.anoPublicacao());
        livro.setEditora(request.editora());
        livro.setQuantidade(request.quantidade());
        return livro;
    }

    // SAÍDA de dados
    public static LivroResponseDTO toDTOLivro(Livro livro) {
        if (livro == null) return null;

        return new LivroResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutores().stream()
                .map(AutorMapper::toDTOAutor)
                .collect(Collectors.toList()),
            CategoriaMapper.toDTOCategoria(livro.getCategoria()),
            livro.getIsbn(),
            livro.getAnoPublicacao(),
            livro.getEditora(),
            livro.getQuantidade()
        );
    }
}