package com.Literalura.Desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaDeNacimiento,
        @JsonAlias("death_year") Integer fechaDeMuerte,
        List<Libro> lista

) {
    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public Integer fechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    @Override
    public Integer fechaDeMuerte() {
        return fechaDeMuerte;
    }

    @Override
    public List<Libro> lista() {
        return lista;
    }
}
