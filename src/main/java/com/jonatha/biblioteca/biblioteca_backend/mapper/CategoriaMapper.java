package com.jonatha.biblioteca.biblioteca_backend.mapper;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria.CategoriaCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.CategoriaResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;

public class CategoriaMapper {
    // ENTRADA de dados
    public static Categoria toEntityCategoria(CategoriaCreateRequestDTO request) {
        if (request == null) return null;

        Categoria categoria = new Categoria();
        categoria.setNome(request.nome());
        categoria.setDescricao(request.descricao());
        return categoria;
    }

    // SAÍDA de dados
    public static CategoriaResponseDTO toDTOCategoria(Categoria categoria) {
        if (categoria == null) return null;

        return new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome(),
            categoria.getDescricao()
        );
    }
}