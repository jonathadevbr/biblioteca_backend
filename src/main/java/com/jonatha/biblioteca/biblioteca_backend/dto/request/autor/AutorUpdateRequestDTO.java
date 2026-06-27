package com.jonatha.biblioteca.biblioteca_backend.dto.request.autor;

import jakarta.validation.constraints.Size;

public record AutorUpdateRequestDTO(

    @Size(min = 1, max = 255, message = "O nome do autor deve ter entre 1 e 255 caracteres")
    String nome,

    @Size(min = 5, max = 255, message = "A nacionalidade do autor deve ter entre 5 e 255 caracteres")
    String nacionalidade
) {
    
}
