package com.jonatha.biblioteca.biblioteca_backend.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.emprestimo.EmprestimoCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.EmprestimoResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.LivroResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.model.Emprestimo;
import com.jonatha.biblioteca.biblioteca_backend.model.Livro;
import com.jonatha.biblioteca.biblioteca_backend.model.Usuario;

public class EmprestimoMapper {

    public static Emprestimo toEntityEmprestimo(EmprestimoCreateRequestDTO request, Usuario usuario, Set<Livro> livros) {
        if (request == null) return null;
        
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivros(livros);
        emprestimo.setDataEmprestimo(request.dataEmprestimo());
        emprestimo.setDataPrevisaoDevolucao(request.dataPrevisaoDevolucao());
        emprestimo.setDataDevolucaoReal(request.dataDevolucaoReal());
        emprestimo.setStatus(request.status());

        return emprestimo;
    }

    public static EmprestimoResponseDTO toDTOEmprestimo(Emprestimo emprestimo) {
        if (emprestimo == null) return null;

        List<LivroResponseDTO> livrosDTO = emprestimo.getLivros().stream()
            .map(LivroMapper::toDTOLivro)
            .collect(Collectors.toList());

        return new EmprestimoResponseDTO(
          emprestimo.getId(),
          UsuarioMapper.toDTOUsuario(emprestimo.getUsuario()),
          livrosDTO,
          emprestimo.getDataEmprestimo(),
          emprestimo.getDataPrevisaoDevolucao(),
          emprestimo.getDataDevolucaoReal(),
          emprestimo.getStatus()
        );
    }
}