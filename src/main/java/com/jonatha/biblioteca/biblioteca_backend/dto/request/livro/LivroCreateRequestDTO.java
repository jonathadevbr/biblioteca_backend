package com.jonatha.biblioteca.biblioteca_backend.dto.request.livro;

import java.util.List;
import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LivroCreateRequestDTO(

    @NotBlank(message = "O Título do livro é obrigatório.")
    @Size(max = 100, message = "O título do livro deve ter no máximo 100 caracteres.")
    String titulo,

    @NotNull(message = "O autor do livro é obrigatório.")
    List<UUID> idsAutores,

    @NotNull(message = "A categoria do livro é obrigatória.")
    UUID idCategoria,

    @NotBlank(message = "O ISBN do livro é obrigatório.")
    @Pattern(regexp = "\\d{13}", message = "ISBN deve conter 13 dígitos números.")
    String isbn,

    @NotNull(message = "O ano de publicação do livro é obrigatório.")
    @Positive(message = "O ano de publicação do livro deve ser maior que zero.")
    Integer anoPublicacao,

    @NotBlank(message = "A editora do livro é obrigatória.")
    String editora,

    @NotNull(message = "A quantidade de livros é obrigatório.")
    @Positive(message = "A quantidade deve ser maior que zero.")
    Integer quantidade
) { }
