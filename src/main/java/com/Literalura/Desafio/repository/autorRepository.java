package com.Literalura.Desafio.repository;

import com.Literalura.Desafio.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface autorRepository extends JpaRepository<Autor, Long> {
    // Puedes agregar métodos personalizados aquí si necesitas realizar consultas específicasas

}
