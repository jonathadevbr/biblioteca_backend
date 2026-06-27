package com.jonatha.biblioteca.biblioteca_backend.dto.request.livro;

import java.util.UUID;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LivroUpdateRequestDTO(

    @Size(min = 5, max = 100, message = "O título do livro deve ter entre 5 e 100 caracteres.")
    String titulo,

    UUID idAutor,

    UUID idCategoria,

    @Pattern(regexp = "\\d{13}", message = "ISBN deve conter 13 dígitos números.")
    String isbn,

    @Positive(message = "O ano de publicação do livro deve ser maior que zero.")
    Integer anoPublicacao,

    String editora,

    @Positive(message = "A quantidade deve ser maior que zero.")
    Integer quantidade

) {

}
