package com.academia.proyecto_final.controller;


import com.academia.proyecto_final.model.Matricula;
import com.academia.proyecto_final.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/matricula")
@RequiredArgsConstructor
public class MatriculaController {

    private final IMatriculaService matriculaService;


    @PostMapping("/createMatriculaRegistration")
    public ResponseEntity<Matricula> saveCourseRecord(@RequestBody Matricula matricula) {
        Matricula MatriculaDetails = matriculaService.create(matricula);
        return new ResponseEntity<>(MatriculaDetails, HttpStatus.CREATED);
    }


}
