package com.academia.proyecto_final.controller;


import com.academia.proyecto_final.model.Matricula;
import com.academia.proyecto_final.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("v1/matricula")
@RequiredArgsConstructor
public class MatriculaController {

    private final IMatriculaService matriculaService;

    @PostMapping("/createMatriculaRegistration")
    public ResponseEntity<Matricula> saveCourseRecord(@RequestBody Matricula matricula) {
        Matricula matriculaDetails = matriculaService.create(matricula);
        return new ResponseEntity<>(matriculaDetails, HttpStatus.CREATED);
    }

    @GetMapping("/listEnrolledCoursesForStudents")
    public ResponseEntity<Map<String, List<String>>> listEnrolledCoursesForStudents() {
        return new ResponseEntity<>(matriculaService.listEnrolledCoursesForStudents(), HttpStatus.OK);
    }

}