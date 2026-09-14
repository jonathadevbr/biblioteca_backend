package com.jonatha.biblioteca.biblioteca_backend.dto.request.livro;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record LivroUpdateRequestDTO(

    @Size(max = 100, message = "O título do livro deve ter no máximo 100 caracteres.")
    String titulo,

    List<UUID> idsAutores,

    UUID idCategoria,

    @Pattern(regexp = "\\d{13}", message = "ISBN deve conter 13 dígitos números.")
    String isbn,

    @PositiveOrZero(message = "O ano de publicação do livro deve ser maior que zero.")
    Integer anoPublicacao,

    String editora,

    @PositiveOrZero(message = "A quantidade deve ser maior que zero.")
    Integer quantidade

) { }
