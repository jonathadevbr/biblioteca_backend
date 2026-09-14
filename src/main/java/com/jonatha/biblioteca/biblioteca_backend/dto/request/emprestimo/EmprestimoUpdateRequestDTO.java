package com.jonatha.biblioteca.biblioteca_backend.dto.request.emprestimo;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jonatha.biblioteca.biblioteca_backend.enums.StatusEmprestimo;


public record EmprestimoUpdateRequestDTO(

    UUID idUsuario,

    List<UUID> idsLivro,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataEmprestimo,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataPrevisaoDevolucao,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataDevolucaoReal,

    StatusEmprestimo status
) { }
