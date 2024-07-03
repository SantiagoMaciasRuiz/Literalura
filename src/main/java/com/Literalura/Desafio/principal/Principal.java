package com.Literalura.Desafio.principal;

import com.Literalura.Desafio.model.Datos;
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
    public void mostrarMenu() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json,Datos.class);
        System.out.println(datos);

    }
}
