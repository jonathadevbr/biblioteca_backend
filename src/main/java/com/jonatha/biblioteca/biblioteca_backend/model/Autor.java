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
@Table(name = "autor", schema = "biblioteca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_autor", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "nacionalidade", nullable = false, length = 255)
    private String nacionalidade;

    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass()) return false;
        
        Autor autor = (Autor) o;
        
        return id != null && id.equals(autor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
