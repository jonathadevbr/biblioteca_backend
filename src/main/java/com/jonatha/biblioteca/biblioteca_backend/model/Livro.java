package com.jonatha.biblioteca.biblioteca_backend.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "livro", schema = "biblioteca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_livro", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @Column(name = "editora", nullable = false)
    private String editora;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

}
