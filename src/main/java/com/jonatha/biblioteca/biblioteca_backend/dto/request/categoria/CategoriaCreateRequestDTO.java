package com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaCreateRequestDTO(

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(max = 255, message = "O nome da categoria deve ter no máximo 255 caracteres")
    String nome,

    @Size(max = 255, message = "A descrição da categoria deve ter no máximo 255 caracteres")
    String descricao

) { }
