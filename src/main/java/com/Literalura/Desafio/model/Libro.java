package com.Literalura.Desafio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer numeroDescargas;
    @Column(unique = true)
    private String titulo;
    private String autores;
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

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
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
                "Autor(es): " + autores + "\n" +
                "Idiomas: " + idiomas + "\n" +
                "Número de descargas: " + numeroDescargas;
        }
    }

