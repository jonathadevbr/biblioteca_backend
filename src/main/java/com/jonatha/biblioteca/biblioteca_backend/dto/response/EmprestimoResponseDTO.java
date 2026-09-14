package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jonatha.biblioteca.biblioteca_backend.enums.StatusEmprestimo;

public record EmprestimoResponseDTO(
    UUID id,
    UsuarioResponseDTO usuario,
    List<LivroResponseDTO> livros,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataEmprestimo,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataPrevisaoDevolucao,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataDevolucaoReal,
    StatusEmprestimo status
) { }
