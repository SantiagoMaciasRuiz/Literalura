package com.Literalura.Desafio.repository;

import com.Literalura.Desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Libro,Long> {

}
