package com.academia.proyecto_final.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDTO {

    private Integer idEstudiante;
    private String nombresEstudiante;
    private String apellidosEstudiante;
    private String DNIEstudiante;
    private Integer edadEstudiante;
}

