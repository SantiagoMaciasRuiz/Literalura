package com.Literalura.Desafio.repository;

import com.Literalura.Desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Long> {

}
