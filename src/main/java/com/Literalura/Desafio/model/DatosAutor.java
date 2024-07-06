package com.Literalura.Desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaDeNacimiento,
        @JsonAlias("death_year") Integer fechaDeMuerte

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
}
