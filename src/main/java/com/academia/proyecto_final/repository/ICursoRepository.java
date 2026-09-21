package com.academia.proyecto_final.repository;

import com.academia.proyecto_final.model.Curso;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends IGenericRepository<Curso, Integer> {
}
