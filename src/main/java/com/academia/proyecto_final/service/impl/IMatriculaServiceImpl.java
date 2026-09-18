package com.academia.proyecto_final.service.impl;

import com.academia.proyecto_final.exception.CursoNotFoundException;
import com.academia.proyecto_final.exception.EstudianteNotFoundException;
import com.academia.proyecto_final.model.Curso;
import com.academia.proyecto_final.model.DetalleMatricula;
import com.academia.proyecto_final.model.Matricula;
import com.academia.proyecto_final.model.Estudiante;
import com.academia.proyecto_final.repository.ICursoRepository;
import com.academia.proyecto_final.repository.IGenericRepository;
import com.academia.proyecto_final.repository.IEstudianteRepository;
import com.academia.proyecto_final.repository.IMatriculaRepository;
import com.academia.proyecto_final.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IMatriculaServiceImpl extends CRUDGenericImpl<Matricula, Integer> implements IMatriculaService {

    private final IMatriculaRepository matriculaRepo;
    private final IEstudianteRepository estudianteRepo;
    private final ICursoRepository cursoRepo;
    @Override
    protected IGenericRepository<Matricula, Integer> getRepository() {
        return matriculaRepo;
    }

    @Override
    public Matricula create(Matricula matricula) {

        Estudiante estudiante = matricula.getEstudiante();
        if (estudiante == null || estudiante.getIdEstudiante() == null) {
            throw new IllegalArgumentException("La matrícula debe incluir el id del estudiante");
        }


        Estudiante estudianteExistente = estudianteRepo.findById(estudiante.getIdEstudiante())
                .orElseThrow(() -> new EstudianteNotFoundException(
                        "No existe el estudiante con id " + estudiante.getIdEstudiante()));

        matricula.setEstudiante(estudianteExistente);

        if (matricula.getDetalleMatricula() != null) {

            /*
            for (DetalleMatricula detalle : matricula.getDetalleMatricula()) {

                Curso curso = detalle.getCurso();
                if (curso == null || curso.getIdCurso() == null) {
                    throw new IllegalArgumentException(
                            "Cada detalle de matrícula debe incluir el id del curso");
                }

                Curso cursoExistente = cursoRepo.findById(curso.getIdCurso())
                        .orElseThrow(() -> new CursoNotFoundException(
                                "No existe el curso con id " + curso.getIdCurso()));

                detalle.setCurso(cursoExistente);
            }*/

        }

        return super.create(matricula);
    }
}
