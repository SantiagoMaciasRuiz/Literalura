package com.Literalura.Desafio.principal;

import com.Literalura.Desafio.model.Datos;
import com.Literalura.Desafio.model.DatosLibros;
import com.Literalura.Desafio.model.Libro;
import com.Literalura.Desafio.repository.libroRepository;
import com.Literalura.Desafio.service.ConsumoAPI;
import com.Literalura.Desafio.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public Principal(libroRepository repositorio) {
        this.repositorio = repositorio;
    }
    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                                  
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
            Libro libro = conversor.convertirDatosLibrosALibro(libroBuscado.get());
            repositorio.save(libro);
            System.out.println("---Libro encontrado---"
                    + "\n" + libro.toString());


        } else {
            System.out.println("Libro no encontrado");
        }
    }
    private void mostrarSeriesBuscadas() {
        libros = repositorio.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }
}
