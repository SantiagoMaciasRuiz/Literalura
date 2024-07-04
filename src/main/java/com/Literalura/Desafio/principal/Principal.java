package com.Literalura.Desafio.principal;

import com.Literalura.Desafio.model.Datos;
import com.Literalura.Desafio.model.DatosLibros;
import com.Literalura.Desafio.model.Libro;
import com.Literalura.Desafio.repository.SerieRepository;
import com.Literalura.Desafio.service.ConsumoAPI;
import com.Literalura.Desafio.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI= new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSQUEDA_API = "?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private SerieRepository repositorio;
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
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorLibro();
                    break;
                case 2:

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
        var datosLibros = conversor.obtenerDatos(json,Datos.class);
        Optional<DatosLibros> libroBuscado = datosLibros.resultados().stream()
                .filter(e -> e.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            repositorio.save(datosLibros);
            System.out.println("---Libro encontrado---"
                    + "\n" + libroBuscado.get());
        }else {
            System.out.println("episodio no encontrado");
        }
    }
}
