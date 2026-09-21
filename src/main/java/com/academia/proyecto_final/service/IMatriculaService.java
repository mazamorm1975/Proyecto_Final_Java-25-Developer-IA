package com.academia.proyecto_final.service;

import com.academia.proyecto_final.model.Matricula;

import java.util.List;
import java.util.Map;

public interface IMatriculaService extends IGenericService<Matricula, Integer>{
    Map<String, List<String>> listEnrolledCoursesForStudents();
}
