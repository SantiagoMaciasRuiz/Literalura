package com.Literalura.Desafio.service;

import com.Literalura.Desafio.model.Autor;
import com.Literalura.Desafio.model.DatosLibros;
import com.Literalura.Desafio.model.Libro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Autowired
    public Libro convertirDatosLibrosALibro(DatosLibros datosLibros) {
        Libro libro = new Libro();
        libro.setTitulo(datosLibros.titulo());
        libro.setIdiomas(String.join(", ", datosLibros.idiomas()));
        libro.setNumeroDescargas(datosLibros.numeroDescargas().intValue());

        // Crear y asociar autores
        List<Autor> autores = datosLibros.autores().stream().map(datosAutor -> {
            Autor autor = new Autor();
            autor.setNombre(datosAutor.nombre());
            autor.setFechaDeNacimiento(datosAutor.fechaDeNacimiento());
            autor.setFechaDeMuerte(datosAutor.fechaDeMuerte());
            autor.setLibro(libro);
            return autor;
        }).collect(Collectors.toList());

        libro.setAutores(autores);

        return libro;
    }
}
