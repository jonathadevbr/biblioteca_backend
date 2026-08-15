package com.jonatha.biblioteca.biblioteca_backend.dto.request.autor;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorCreateRequestDTO(

    @NotBlank(message = "O nome do autor é obrigatório.")
    @Size(max = 255, message = "O nome do autor deve ter no máximo 255 caracteres")
    String nome,

    @NotBlank(message = "A nacionalidade do autor é obrigatória.")
    @Size(min = 5, max = 255, message = "A nacionalidade do autor deve ter entre 5 e 255 caracteres")
    String nacionalidade

) { }
