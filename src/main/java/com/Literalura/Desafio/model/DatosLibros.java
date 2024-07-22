package com.Literalura.Desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("languages")  List<String> idiomas,
        @JsonAlias("download_count") Integer numeroDescargas
) {

    @Override
    public String titulo() {
        return titulo;
    }

    @Override
    public List<DatosAutor> autor() {
        return autor;
    }

    @Override
    public List<String> idiomas() {
        return idiomas;
    }

    @Override
    public Integer numeroDescargas() {
        return numeroDescargas;
    }
}

