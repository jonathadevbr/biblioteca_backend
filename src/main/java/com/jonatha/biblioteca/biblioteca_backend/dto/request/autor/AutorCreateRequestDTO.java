package com.jonatha.biblioteca.biblioteca_backend.dto.request.autor;

import com.jonatha.biblioteca.biblioteca_backend.model.Autor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorCreateRequestDTO(

    @NotBlank(message = "O nome do autor é obrigatório.")
    @Size(min = 1, max = 255, message = "O nome do autor deve ter entre 1 e 255 caracteres")
    String nome,

    @NotBlank(message = "A nacionalidade do autor é obrigatório.")
    @Size(min = 5, max = 255, message = "A nacionalidade do autor deve ter entre 5 e 255 caracteres")
    String nacionalidade

) {
    public Autor createAutor() {
        Autor entity = new Autor();

        entity.setNome(this.nome);
        entity.setNacionalidade(this.nacionalidade);

        return entity;
    }
}
