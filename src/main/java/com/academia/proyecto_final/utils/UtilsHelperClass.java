package com.academia.proyecto_final.utils;

import com.academia.proyecto_final.dto.EstudianteDTO;
import com.academia.proyecto_final.exception.ResourceNotFoundException;
import com.academia.proyecto_final.model.Estudiante;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilsHelperClass {

        private final ModelMapper mapper;

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

        public EstudianteDTO toDTO(Estudiante estudiante) {
            return mapper.map(estudiante, EstudianteDTO.class);
        }

        public  Estudiante toEntity(EstudianteDTO estudianteDTO){
            return mapper.map(estudianteDTO, Estudiante.class);
        }

}
