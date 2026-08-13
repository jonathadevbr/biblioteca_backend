package com.jonatha.biblioteca.biblioteca_backend.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jonatha.biblioteca.biblioteca_backend.enums.StatusEmprestimo;
import com.jonatha.biblioteca.biblioteca_backend.model.Emprestimo;

public record EmprestimoResponseDTO(
    UUID id,
    UsuarioResponseDTO usuario,
    List<LivroResponseDTO> livros,
    LocalDate dataEmprestimo,
    LocalDate dataPrevisaoDevolucao,
    LocalDate dataDevolucaoReal,
    StatusEmprestimo status
) {
    public EmprestimoResponseDTO(Emprestimo emprestimo) {
        this( 
            emprestimo.getId(),
            new UsuarioResponseDTO(emprestimo.getUsuario()),
            emprestimo.getLivros().stream().map(LivroResponseDTO::new).collect(Collectors.toList()),
            emprestimo.getDataEmprestimo(),
            emprestimo.getDataPrevisaoDevolucao(),
            emprestimo.getDataDevolucaoReal(),
            emprestimo.getStatus()
        );
    }
}
