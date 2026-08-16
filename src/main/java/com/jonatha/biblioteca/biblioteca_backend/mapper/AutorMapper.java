package com.jonatha.biblioteca.biblioteca_backend.mapper;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.autor.AutorCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.AutorResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.model.Autor;

public class AutorMapper {
    // ENTRADA de dados
    public static Autor toEntityAutor(AutorCreateRequestDTO request) {
        if (request == null) return null;

        Autor autor = new Autor();
        autor.setNome(request.nome());
        autor.setNacionalidade(request.nacionalidade());
        return autor;
    }

    // SAÍDA de dados
    public static AutorResponseDTO toDTOAutor(Autor autor) {
        if (autor == null) return null;

        return new AutorResponseDTO(
            autor.getId(),
            autor.getNome(),
            autor.getNacionalidade()
        );
    }

}
