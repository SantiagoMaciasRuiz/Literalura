package com.Literalura.Desafio.principal;

import com.Literalura.Desafio.model.*;
import com.Literalura.Desafio.repository.autorRepository;
import com.Literalura.Desafio.repository.libroRepository;
import com.Literalura.Desafio.service.ConsumoAPI;
import com.Literalura.Desafio.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI= new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSQUEDA_API = "?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> libros;
    private libroRepository repositorio;
    private autorRepository repositorioAutor;
    @Autowired
    public Principal(libroRepository repositorio,autorRepository  repositorioAutor) {
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
    }
    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 -Buscar Libro
                    2 -Lista e busquedas
                    3 -lista de autores buscados por libro
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.next(); // Limpiar el buffer
                continue;
            }
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    buscarLibroPorLibro();
                    break;
                case 2:
                    mostrarSeriesBuscadas();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    //metodo para buscar libros por titulo
    private void buscarLibroPorLibro() {
        System.out.println("Que libro deseas buscar?");
        var nombreLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + BUSQUEDA_API + nombreLibro.replace(" ","%20"));
        var datosLibros = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosLibros.resultados().stream()
                .filter(e -> e.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            try {
                // Convertir DatosLibros a Libro
                Libro libro = conversor.convertirDatosLibrosALibro(libroBuscado.get());

                // Guardar libro y autores
                repositorio.save(libro);
                System.out.println("---Libro encontrado---\n" + libro.toString());
            } catch (DataIntegrityViolationException e) {
                System.out.println("El libro ya se encuentra en la base de datos");
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void mostrarSeriesBuscadas() {
        libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se han buscado libros.");
        } else {
            libros.stream().sorted(Comparator.comparing(Libro::getTitulo))
                    .forEach(System.out::println);
        }
    }
    private void mostrarAutores() {
        List<Autor> autores = repositorioAutor.findAll();
            autores.stream().sorted(Comparator.comparing(Autor::getNombre)).distinct()
                    .forEach(System.out::println);

    }
}
