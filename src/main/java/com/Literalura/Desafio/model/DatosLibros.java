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
        @JsonAlias("download_count") Double numeroDescargas
) {
    @Override
    public String toString() {
        String nombresAutores = autor.stream()
                .map(DatosAutor::nombre) // Asumiendo que DatosAutor tiene el método getNombre()
                .collect(Collectors.joining(", "));
        return "--- Libro ---\n" +
                "Título: " + titulo + "\n" +
                "Autor(es): " + nombresAutores + "\n" +
                "Idiomas: " + idiomas + "\n" +
                "Número de descargas: " + numeroDescargas;
    }
}

