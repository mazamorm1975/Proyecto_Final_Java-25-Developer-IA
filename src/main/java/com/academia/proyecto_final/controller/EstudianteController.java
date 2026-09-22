package com.academia.proyecto_final.controller;


import com.academia.proyecto_final.dto.EstudianteDTO;
import com.academia.proyecto_final.model.Estudiante;
import com.academia.proyecto_final.service.IEstudianteService;
import com.academia.proyecto_final.utils.UtilsHelperClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/estudiante")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService estudianteService;

    private final UtilsHelperClass mapper;


    @GetMapping("/generalStudentList")
    public ResponseEntity<List<EstudianteDTO>> listadoGeneralEstudiantes(){

        List<EstudianteDTO> listadoEdadEstudiante = estudianteService.findAll()
                .stream()
                .map(mapper::toStudentDTO).toList();

        return new ResponseEntity<>(listadoEdadEstudiante, HttpStatus.OK);
    }


    @GetMapping("/listarEstudiantePorId/{idStudent}")
    public ResponseEntity<EstudianteDTO> listado(@PathVariable("idStudent") Integer idStudent){

        Estudiante student = estudianteService.findById(idStudent);

        return new ResponseEntity<>(mapper.toStudentDTO(student), HttpStatus.OK);
    }

    @GetMapping("/studentListByAscOrder")
    public ResponseEntity<List<EstudianteDTO>> studentListByAscOrder(){

        //El metodo findAllByOrderByEdadDesc() obtiene una lista ordenes de estudiantes por edad en orden descendente.
        List<Estudiante> listByEdadAscOrder = estudianteService.findAllByOrderByEdadDesc();
        List<EstudianteDTO> listaEstudianteDTO = listByEdadAscOrder.stream().map(x -> mapper.toStudentDTO(x)).toList();

        return new ResponseEntity<>(listaEstudianteDTO, HttpStatus.OK);
    }

    @PostMapping("/createRegistration")
    public ResponseEntity<EstudianteDTO> saveStudentRecord(@RequestBody EstudianteDTO studentDTO){
        Estudiante studentDetails = estudianteService.create(mapper.toStudentEntity(studentDTO));
        return new ResponseEntity<>(mapper.toStudentDTO(studentDetails), HttpStatus.CREATED);
    }

    @PutMapping("/updateStudentRecord/{idStudent}")
    public ResponseEntity<EstudianteDTO> updateStudentRecord(@RequestBody EstudianteDTO studentDTO, @PathVariable("idStudent") Integer idStudent) throws Exception {
        Estudiante studentUpdate =  estudianteService.update(mapper.toStudentEntity(studentDTO),idStudent);
        return new ResponseEntity<>(mapper.toStudentDTO(studentUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/studentRecordDeletion/{idStudent}")
    public ResponseEntity<Void> deleteStudenRecord(@PathVariable("idStudent") Integer idStudent){
        estudianteService.delete(idStudent);
        return ResponseEntity.noContent().build();
    }

}