package com.academia.proyecto_final.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {

    private Integer idCurso;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nombreCurso;

    @NotBlank
    @Size(min = 2, max = 10)
    private String siglasCurso;

    @NotNull
    private Boolean estadoCurso;
}
