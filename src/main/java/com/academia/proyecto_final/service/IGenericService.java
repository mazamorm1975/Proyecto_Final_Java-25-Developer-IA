package com.academia.proyecto_final.service;


import com.academia.proyecto_final.model.Estudiante;

import java.io.IOException;
import java.util.List;

public interface IGenericService <T, ID>{

   List<T> findAll();
   T findById(ID id);
   T create(T t);
   T update(T t, ID id) throws  Exception;
   void delete(ID id);
}
