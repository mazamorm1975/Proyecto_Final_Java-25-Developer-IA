package com.academia.proyecto_final.service;

import com.academia.proyecto_final.model.Estudiante;

import java.util.List;


public interface IEstudianteService extends IGenericService<Estudiante, Integer>{
    List<Estudiante> findAllByOrderByEdadDesc();
}
