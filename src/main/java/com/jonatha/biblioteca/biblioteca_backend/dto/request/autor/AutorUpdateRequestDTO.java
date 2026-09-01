package com.jonatha.biblioteca.biblioteca_backend.dto.request.autor;

import jakarta.validation.constraints.Size;

public record AutorUpdateRequestDTO(

    @Size(max = 255, message = "O nome do autor deve ter no máximo 255 caracteres")
    String nome,

    @Size(max = 255, message = "A nacionalidade do autor deve ter no máximo 255 caracteres")
    String nacionalidade
) { }
