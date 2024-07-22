package com.Literalura.Desafio.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer numeroDescargas;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;
    private String idiomas;
    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "--- Libro ---\n" +
                "Título: " + titulo + "\n" +
                "Autor(es): " + autor.getNombre() + "\n" +
                "Idiomas: " + idiomas + "\n" +
                "Número de descargas: " + numeroDescargas + "\n"
                +"==================";
        }

    public void setAutor() {
    }
}

