package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.jonatha.biblioteca.biblioteca_backend.enums.StatusEmprestimo;

public record EmprestimoResponseDTO(
    UUID id,
    UsuarioResponseDTO usuario,
    List<LivroResponseDTO> livros,
    LocalDate dataEmprestimo,
    LocalDate dataPrevisaoDevolucao,
    LocalDate dataDevolucaoReal,
    StatusEmprestimo status
) { }
