package com.jonatha.biblioteca.biblioteca_backend.dto.request.emprestimo;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import com.jonatha.biblioteca.biblioteca_backend.enums.StatusEmprestimo;

import jakarta.validation.constraints.NotNull;

public record EmprestimoCreateRequestDTO(

    @NotNull(message = "O ID do usuário é obrigatório.")
    UUID idUsuario,

    @NotNull(message = "O ID do livro é obrigatório.")
    List<UUID> idsLivro,

    @NotNull(message = "A data inicial do emprestimo é obrigatória.")
    LocalDate dataEmprestimo,

    @NotNull(message = "A data de previsão de devolução é obrigatória.")
    LocalDate dataPrevisaoDevolucao,

    LocalDate dataDevolucaoReal,

    @NotNull(message = "O tipo de status do empréstimo é obrigatório.")
    StatusEmprestimo status
) { }
