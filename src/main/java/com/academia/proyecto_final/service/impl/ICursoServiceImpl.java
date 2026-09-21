package com.academia.proyecto_final.service.impl;

import com.academia.proyecto_final.exception.CursoNotFoundException;
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

    // CRUDGenericImpl.findById lanza EstudianteNotFoundException; aquí se mapea al 404 de Curso
    @Override
    public Curso findById(Integer id) {
        return cursoRepo.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("COURSE ID NOT FOUND " + id));
    }
}
