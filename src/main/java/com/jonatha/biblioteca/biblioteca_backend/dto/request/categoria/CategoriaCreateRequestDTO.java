package com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria;

import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaCreateRequestDTO(

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(min = 5, max = 255, message = "O nome da categoria deve ter entre 5 e 255 caracteres")
    String nome,

    @Size(min = 5, max = 255, message = "O nome da categoria deve ter entre 5 e 255 caracteres")
    String descricao

) {
    public Categoria createCategoria() {
        Categoria categoria = new Categoria();

        categoria.setNome(this.nome);
        categoria.setDescricao(this.descricao);

        return categoria;
    }
}
