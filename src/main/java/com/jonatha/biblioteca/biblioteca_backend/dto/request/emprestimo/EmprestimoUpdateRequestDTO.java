package com.jonatha.biblioteca.biblioteca_backend.dto.request.emprestimo;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import com.jonatha.biblioteca.biblioteca_backend.enums.StatusEmprestimo;


public record EmprestimoUpdateRequestDTO(

    UUID idUsuario,

    List<UUID> idsLivro,

    LocalDate dataEmprestimo,

    LocalDate dataPrevisaoDevolucao,

    LocalDate dataDevolucaoReal,

    StatusEmprestimo status
) {

}
