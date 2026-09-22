package com.academia.proyecto_final.utils;

import com.academia.proyecto_final.dto.CursoDTO;
import com.academia.proyecto_final.dto.EstudianteDTO;
import com.academia.proyecto_final.exception.ResourceNotFoundException;
import com.academia.proyecto_final.model.Curso;
import com.academia.proyecto_final.model.Estudiante;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilsHelperClass {

    private final ModelMapper mapper;

        /*
        public static <ID> RuntimeException createNotFoundException(
                Class<?> entityClass, ID id) {
            String exceptionClassName = "com.academia.proyecto_final.exception."
                    + entityClass.getSimpleName() + "NotFoundException";
            String message = entityClass.getSimpleName().toUpperCase()
                    + " ID NOT FOUND " + id;

            try {
                Class<?> exceptionClass = Class.forName(exceptionClassName);
                return (RuntimeException) exceptionClass
                        .getConstructor(String.class)
                        .newInstance(message);
            } catch (ClassNotFoundException exception) {
                return new ResourceNotFoundException(message);
            } catch (ReflectiveOperationException exception) {
                throw new IllegalStateException(
                        "Could not create not found exception for " + entityClass.getSimpleName(),
                        exception);
            }
        }
        */

    public EstudianteDTO toStudentDTO(Estudiante estudiante) {
        return mapper.map(estudiante, EstudianteDTO.class);
    }

    public Estudiante toStudentEntity(EstudianteDTO estudianteDTO) {
        return mapper.map(estudianteDTO, Estudiante.class);
    }

    public CursoDTO toCursoDTO(Curso curso) {
        return mapper.map(curso, CursoDTO.class);
    }

    public Curso toCursoEntity(CursoDTO cursoDTO) {
        return mapper.map(cursoDTO, Curso.class);
    }

}
