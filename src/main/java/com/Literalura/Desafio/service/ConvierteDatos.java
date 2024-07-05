package com.Literalura.Desafio.service;

import com.Literalura.Desafio.model.Autor;
import com.Literalura.Desafio.model.DatosAutor;
import com.Literalura.Desafio.model.DatosLibros;
import com.Literalura.Desafio.model.Libro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
        libro.setAutores(datosLibros.autor().stream()
                .map(a -> a.nombre())
                .collect(Collectors.joining(", ")));
        libro.setIdiomas(String.join(", ", datosLibros.idiomas()));
        libro.setNumeroDescargas(datosLibros.numeroDescargas().intValue());
        return libro;
    }
    @Autowired
    public Autor convertirDatosAutorAAutor(DatosAutor datosAutor) {
        Autor autor = new Autor();
        autor.setNombre(datosAutor.nombre());
        autor.setFechaDeNacimiento(datosAutor.fechaDeNacimiento());
        autor.setFechaDeMuerte(datosAutor.fechaDeMuerte());
        autor.setLibro();
    }

}
