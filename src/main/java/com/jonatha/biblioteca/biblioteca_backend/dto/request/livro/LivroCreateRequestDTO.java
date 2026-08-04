package com.jonatha.biblioteca.biblioteca_backend.dto.request.livro;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.jonatha.biblioteca.biblioteca_backend.model.Autor;
import com.jonatha.biblioteca.biblioteca_backend.model.Categoria;
import com.jonatha.biblioteca.biblioteca_backend.model.Livro;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LivroCreateRequestDTO(

    @NotBlank(message = "O Título do livro é obrigatório.")
    @Size(min = 1, max = 100, message = "O título do livro deve ter entre 5 e 100 caracteres.")
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
) {
    public Livro createLivro(Set<Autor> autores, Categoria categoria) {
        Livro livro = new Livro();

        livro.setTitulo(this.titulo);
        livro.setAutores(autores);
        livro.setCategoria(categoria);
        livro.setIsbn(this.isbn);
        livro.setAnoPublicacao(this.anoPublicacao);
        livro.setEditora(this.editora);
        livro.setQuantidade(this.quantidade);

        return livro;
    }
}
