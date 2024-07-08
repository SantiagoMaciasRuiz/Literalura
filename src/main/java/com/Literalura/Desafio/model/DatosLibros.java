package com.Literalura.Desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages")  List<String> idiomas,
        @JsonAlias("download_count") Integer numeroDescargas
) {
    @Override
    public String titulo() {
        return titulo;
    }


    @Override
    public List<DatosAutor> autores() {
        return autores;
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

