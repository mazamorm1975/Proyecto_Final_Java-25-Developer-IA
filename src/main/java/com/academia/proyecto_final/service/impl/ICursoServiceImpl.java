package com.academia.proyecto_final.service.impl;

import com.academia.proyecto_final.model.Curso;
import com.academia.proyecto_final.repository.ICursoRepository;
import com.academia.proyecto_final.repository.IGenericRepository;
import com.academia.proyecto_final.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ICursoServiceImpl extends CRUDGenericImpl<Curso, Integer> implements ICursoService {

    private final ICursoRepository cursoRepo;

    @Override
    protected IGenericRepository<Curso, Integer> getRepository() {
        return cursoRepo;
    }
}
