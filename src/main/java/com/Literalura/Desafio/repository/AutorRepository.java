package com.Literalura.Desafio.repository;

import com.Literalura.Desafio.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombre(String nombre);
    List<Autor> findByFechaDeNacimientoGreaterThanEqualAndFechaDeMuerteLessThanEqual(Integer fechaDeNacimiento, Integer fechaDeMuerte);


    //otra opcion ->
    @Query("select a from Autor a WHERE a.fechaDeMuerte <= :fechaDeMuerte AND a.fechaDeNacimiento >= :fechaDeNacimiento")
    List<Autor> AutoresVivosEnDeterminadaFecha(int fechaDeNacimiento,int fechaDeMuerte);
}
