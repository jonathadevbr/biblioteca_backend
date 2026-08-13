package com.jonatha.biblioteca.biblioteca_backend.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonatha.biblioteca.biblioteca_backend.dto.request.emprestimo.EmprestimoCreateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.request.emprestimo.EmprestimoUpdateRequestDTO;
import com.jonatha.biblioteca.biblioteca_backend.dto.response.EmprestimoResponseDTO;
import com.jonatha.biblioteca.biblioteca_backend.exception.NotFoundException;
import com.jonatha.biblioteca.biblioteca_backend.model.Emprestimo;
import com.jonatha.biblioteca.biblioteca_backend.model.Livro;
import com.jonatha.biblioteca.biblioteca_backend.model.Usuario;
import com.jonatha.biblioteca.biblioteca_backend.repository.EmprestimoRepository;
import com.jonatha.biblioteca.biblioteca_backend.repository.LivroRepository;
import com.jonatha.biblioteca.biblioteca_backend.repository.UsuarioRepository;

@Service
public class EmprestimoService {
    
    private final EmprestimoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(
        EmprestimoRepository repository,
        UsuarioRepository usuarioRepository,
        LivroRepository livroRepository
    ) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> getAllEmprestimoService(Pageable pageable) {
        return repository.findAll(pageable).map(EmprestimoResponseDTO::new);
    }

    @Transactional
    public EmprestimoResponseDTO createEmprestimoSerivce(EmprestimoCreateRequestDTO request) {

        Set<Livro> livros = new HashSet<>(livroRepository.findAllById(request.idsLivro()));

        Usuario usuario = usuarioRepository.findById(request.idUsuario())
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        Emprestimo emprestimo = request.createEmprestimo(livros, usuario);

        emprestimo = repository.save(emprestimo);
        return new EmprestimoResponseDTO(emprestimo);
    }

    @Transactional
    public EmprestimoResponseDTO getEmprestimoService(UUID id) {
        Emprestimo emprestimo = buscarEmprestimoPorId(id);

        return new EmprestimoResponseDTO(emprestimo);
    }

    @Transactional
    public EmprestimoResponseDTO updateEmprestimoService(UUID id, EmprestimoUpdateRequestDTO request) {
        Emprestimo emprestimo = buscarEmprestimoPorId(id);

        if (request.idUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
            emprestimo.setUsuario(usuario);
        }

        if (request.idsLivro() != null) {
            Set<Livro> livros = new HashSet<>(livroRepository.findAllById(request.idsLivro()));

            if (livros.size() != new HashSet<>(request.idsLivro()).size()) {
                throw new NotFoundException("Um ou mais livros não foram encontrados.");
            }
        }

        if (request.dataEmprestimo() != null) {
            emprestimo.setDataEmprestimo(request.dataEmprestimo());
        }

        if (request.dataPrevisaoDevolucao() != null) {
            emprestimo.setDataPrevisaoDevolucao(request.dataPrevisaoDevolucao());
        }

        if (request.dataDevolucaoReal() != null) {
            emprestimo.setDataDevolucaoReal(request.dataDevolucaoReal());
        }

        if (request.status() != null) {
            emprestimo.setStatus(request.status());
        }
        
        emprestimo = repository.save(emprestimo);
        return new EmprestimoResponseDTO(emprestimo);
         
    }

    private Emprestimo buscarEmprestimoPorId(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado no sistema."));
    }
}
