package com.Literalura.Desafio.principal;

import com.Literalura.Desafio.model.Autor;
import com.Literalura.Desafio.model.Datos;
import com.Literalura.Desafio.model.DatosLibros;
import com.Literalura.Desafio.model.Libro;
import com.Literalura.Desafio.repository.AutorRepository;
import com.Literalura.Desafio.repository.LibroRepository;
import com.Literalura.Desafio.service.ConsumoAPI;
import com.Literalura.Desafio.service.ConvierteDatos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSQUEDA_API = "?search=";
    private final ConvierteDatos conversor;

    private final LibroRepository libroRepository;
    private final AutorRepository repositorioAutor;

    @Autowired
    public Principal(LibroRepository libroRepository, AutorRepository repositorioAutor, ConvierteDatos conversor) {
        this.libroRepository = libroRepository;
        this.repositorioAutor = repositorioAutor;
        this.conversor = conversor;
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    1 - Buscar Libros 
                    2 - Listar Libros buscados
                    3 - Listar Libros por autores
                    4 - Listar Segun Año de Muerte y Nacimiento            
                    ==========================================
                    Estadisticas:
                    5 - Mostrar Estadisticas de la Base de Datos  
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
                case 1 -> buscarLibroPorLibro();
                case 2 -> ListarLibrosBuscados();
                case 3 -> ListarAutorPorLibro();
                case 4 -> autoresVivosFecha();
                case 5 -> EstadisticasLibros();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    // Método para buscar libros por título
    @Transactional
    private void buscarLibroPorLibro() {
        System.out.println("¿Qué libro deseas buscar?");
        String nombreLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + BUSQUEDA_API + nombreLibro.replace(" ", "%20"));
        // Imprimir el JSON para verificar el formato
        System.out.println("JSON recibido: " + json);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datos.resultados().stream()
                .filter(e -> e.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();


        if (libroBuscado.isPresent()) {
            Libro libro = conversor.convertirDatosLibrosALibro(libroBuscado.get());

            // Primero guarda el autor
            repositorioAutor.save(libro.getAutor());

            // Luego guarda el libro
            libroRepository.save(libro);

            System.out.println("---Libro encontrado---\n" + libro.toString());
        } else {
            System.out.println("Libro no encontrado");
        }
    }
    @Transactional
    public void ListarLibrosBuscados(){
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la base de datos.");
        } else {
            libros.forEach(libro -> System.out.println(libro.toString()));
        }
    }
    @Transactional
    public void ListarAutorPorLibro(){
        // Obtener todos los autores
        List<Autor> autores = repositorioAutor.findAll();
        List<String> listaAutores = new ArrayList<>();
        if (autores.isEmpty()) {
            System.out.println("No hay autores en la base de datos.");
            return;
        }

        // Iterar sobre cada autor y obtener sus libros
        for (Autor autor : autores) {
            System.out.println("Autor: " + autor.getNombre()
                            +"\n"+"Fecha De Nacimiento:" + autor.getFechaDeNacimiento()+"\n"+
                    "Fecha De Muerte:" + autor.getFechaDeMuerte()
                    );

            // Obtener los libros del autor
            Set<Libro> libros = autor.getLibros();

            if (libros.isEmpty()) {
                System.out.println("No hay libros para librose autor.");
            } else {
                System.out.println("Libros:");
                for (Libro libro : libros) {
                    listaAutores.add(libro.getTitulo());
                }
                System.out.println(listaAutores+"\n"+"=========================");
                listaAutores.clear();
            }
        }
    }
    @Transactional
    public void autoresVivosFecha(){
        System.out.println("Ingresa La fecha inicial de nacimiento:");
        int fechaDeNacimiento = teclado.nextInt();
        System.out.println("La fecha de muerte sobre el autor:");
        int fechaDeMuerte = teclado.nextInt();
        List<Autor> autores = repositorioAutor.findByFechaDeNacimientoGreaterThanEqualAndFechaDeMuerteLessThanEqual(fechaDeNacimiento,fechaDeMuerte);
        for (Autor autor : autores) {
            System.out.println(conversor.formatearAutor(autor));
        }
    }
    @Transactional
    public void EstadisticasLibros() {
        List<Libro> libros = libroRepository.findAll();
        IntSummaryStatistics stats =  libros.stream().collect(Collectors.summarizingInt(Libro::getNumeroDescargas));
        System.out.println("Media:" + stats.getAverage() +
                "\nNumero mayor de descargas:" + stats.getMax() +
                "\nNumero menor de descargas:" + stats.getMin() +
                "\nCantidad de registros:" + stats.getCount()
        + "\n====================================================");
    }
}