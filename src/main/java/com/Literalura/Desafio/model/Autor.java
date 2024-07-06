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
    @JoinColumn(name = "Libros_id")
    private Libro libro;





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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Autor:" + nombre + '\n' +
                "Fecha De Nacimiento:"+ fechaDeNacimiento +"\n" +
                "Fecha De Muerte:" + fechaDeMuerte + "\n" +
                "libros:" + libro.getTitulo();
    }
}
