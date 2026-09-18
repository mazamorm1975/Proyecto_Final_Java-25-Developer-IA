package com.academia.proyecto_final.controller;

import com.academia.proyecto_final.exception.EstudianteNotFoundException;
import com.academia.proyecto_final.model.Estudiante;
import com.academia.proyecto_final.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@RestController
@RequestMapping("v1/estudiante")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @GetMapping("/generalStudentList")
    public ResponseEntity<List<Estudiante>> listadoGeneralEstudiantes(){
        //Se obtiene un listado general de todos los estudiantes de la academia
        Function<Estudiante, Integer> estudiante = x -> x.getEdad();
        List<Estudiante> listadoEdadEstudiante = estudianteService.findAll()
              .stream()
              .sorted(Comparator.comparing(estudiante).reversed()).toList();

      return new ResponseEntity<>(listadoEdadEstudiante, HttpStatus.OK);
    }

    @PostMapping("/createRegistration")
    public ResponseEntity<Estudiante> saveStudentRecord(@RequestBody Estudiante student){
       Estudiante studentDetails = estudianteService.create(student);
       return new ResponseEntity<>(studentDetails, HttpStatus.CREATED);
    }

    @PutMapping("/updateStudentRecord/{idStudent}")
    public ResponseEntity<Estudiante> updateStudentRecord(@RequestBody Estudiante student, @PathVariable("idStudent") Integer idStudent) throws Exception {
       Estudiante studentUpdate =  estudianteService.update(student,idStudent);
      return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/studentRecordDeletion/{idStudent}")
    public ResponseEntity<Void> deleteStudenRecord(@PathVariable("idStudent") Integer idStudent){
        estudianteService.delete(idStudent);
        return ResponseEntity.noContent().build();
    }



}
