package com.jonatha.biblioteca.biblioteca_backend.model;

import java.time.LocalDate;
import java.util.UUID;

import com.jonatha.biblioteca.enums.StatusEmpretimos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emprestimo", schema = "biblioteca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_emprestimo", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_previsao_devolucao", nullable = false)
    private LocalDate dataPrevisaoDevolucao;

    @Column(name = "data_devolucao_real")
    private LocalDate dataDevolucaoReal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEmpretimos status;
}
