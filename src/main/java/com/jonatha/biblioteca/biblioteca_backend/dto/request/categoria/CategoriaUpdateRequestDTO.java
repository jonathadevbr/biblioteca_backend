package com.jonatha.biblioteca.biblioteca_backend.dto.request.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaUpdateRequestDTO(

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(min = 5, max = 255, message = "O nome da categoria deve ter entre 5 e 255 caracteres")
    String nome,

    @Size(min = 5, max = 255, message = "O nome da categoria deve ter entre 5 e 255 caracteres")
    String descricao

) {
    
}
