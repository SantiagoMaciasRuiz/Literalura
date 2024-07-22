package com.Literalura.Desafio.service;

import com.Literalura.Desafio.model.Autor;
import com.Literalura.Desafio.model.DatosAutor;
import com.Literalura.Desafio.model.DatosLibros;
import com.Literalura.Desafio.model.Libro;
import com.Literalura.Desafio.repository.AutorRepository;
import com.Literalura.Desafio.repository.LibroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Libro convertirDatosLibrosALibro(DatosLibros datosLibros) {
        Libro libro = new Libro();
        libro.setTitulo(datosLibros.titulo());

        if (!datosLibros.autor().isEmpty()) {
            DatosAutor datosAutor = datosLibros.autor().get(0);
            Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                    .orElseGet(() -> {
                        Autor nuevoAutor = new Autor();
                        nuevoAutor.setNombre(datosAutor.nombre());
                        nuevoAutor.setFechaDeNacimiento(datosAutor.fechaDeNacimiento());
                        nuevoAutor.setFechaDeMuerte(datosAutor.fechaDeMuerte());
                        return autorRepository.save(nuevoAutor);
                    });
            libro.setAutor(autor);
        } else {
            throw new RuntimeException("El libro debe tener al menos un autor");
        }

        libro.setIdiomas(String.join(", ", datosLibros.idiomas()));
        libro.setNumeroDescargas(datosLibros.numeroDescargas());
        return libro;
    }
}
