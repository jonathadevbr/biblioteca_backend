package com.jonatha.biblioteca.biblioteca_backend.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
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

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            schema = "biblioteca",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_autor")
    )
    private Set<Autor> autores = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "isbn", nullable = false, unique = true, length = 13)
    private String isbn;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;
    
    @Column(name = "editora", nullable = false, length = 100)
    private String editora;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Livro livro = (Livro) o;
        
        return isbn != null && isbn.equals(livro.isbn);
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : getClass().hashCode();
    }
}
