package com.Literalura.Desafio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;

    @ManyToOne
    @JoinColumn(name = "libros_id") // Cambiado a "libro_id" para coincidir con el nombre de la columna en la tabla
    private Libro libro;

    // Getters y Setters omitidos para brevedad


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        String libroTitulo = (libro != null) ? libro.getTitulo() : "Sin libro asociado"; // Verifica si libro es null

        return "--------------------------------------\n" +
                "Autor: " + nombre + '\n' +
                "Fecha De Nacimiento: " + fechaDeNacimiento + "\n" +
                "Fecha De Muerte: " + fechaDeMuerte + "\n" +
                "Libro: " + libroTitulo + "\n" +
                "--------------------------------------";
    }
}
