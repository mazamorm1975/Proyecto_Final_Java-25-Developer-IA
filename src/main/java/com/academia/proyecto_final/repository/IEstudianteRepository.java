package com.academia.proyecto_final.repository;

import com.academia.proyecto_final.model.Estudiante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEstudianteRepository extends IGenericRepository<Estudiante,  Integer> {
    List<Estudiante> findAllByOrderByEdadDesc();

}
