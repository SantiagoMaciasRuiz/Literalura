package com.Literalura.Desafio.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer numeroDescargas;
    @Column(unique = true)
    private String titulo;
    @OneToMany(mappedBy = "libro" , cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<>();
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
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
                "Autor(es): " + autores.stream()
                .map(autor -> autor.getNombre())
                .collect(Collectors.joining(", ")) + "\n" +
                "Idiomas: " + idiomas + "\n" +
                "Número de descargas: " + numeroDescargas+ "\n"
                + "___________________";
        }
    }

