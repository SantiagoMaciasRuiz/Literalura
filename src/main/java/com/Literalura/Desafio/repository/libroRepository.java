package com.Literalura.Desafio.repository;

import com.Literalura.Desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface libroRepository extends JpaRepository<Libro,Long> {

}
