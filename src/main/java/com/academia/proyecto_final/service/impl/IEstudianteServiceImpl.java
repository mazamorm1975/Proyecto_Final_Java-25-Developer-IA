package com.academia.proyecto_final.service.impl;

import com.academia.proyecto_final.model.Estudiante;
import com.academia.proyecto_final.repository.IEstudianteRepository;
import com.academia.proyecto_final.repository.IGenericRepository;
import com.academia.proyecto_final.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IEstudianteServiceImpl extends CRUDGenericImpl<Estudiante, Integer> implements IEstudianteService {

    private final IEstudianteRepository studentRepo;


    @Override
    protected IGenericRepository<Estudiante, Integer> getRepository() {
        return studentRepo;
    }


}
