package com.jonatha.biblioteca.biblioteca_backend.dto.request.livro;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LivroUpdateRequestDTO(

    @Size(min = 1, max = 100, message = "O título do livro deve ter entre 5 e 100 caracteres.")
    String titulo,

    List<UUID> idsAutores,

    UUID idCategoria,

    @Positive(message = "O ano de publicação do livro deve ser maior que zero.")
    Integer anoPublicacao,

    String editora,

    @Positive(message = "A quantidade deve ser maior que zero.")
    Integer quantidade

) {

}
