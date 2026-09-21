package com.academia.proyecto_final.controller;

import com.academia.proyecto_final.dto.CursoDTO;
import com.academia.proyecto_final.model.Curso;
import com.academia.proyecto_final.service.ICursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/curso")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService cursoService;

    private final ModelMapper mapper;

    @GetMapping("/generalCourseList")
    public ResponseEntity<List<CursoDTO>> listadoGeneralCursos() {
        List<CursoDTO> listadoCursos = cursoService.findAll()
                .stream()
                .map(x -> mapper.map(x, CursoDTO.class)).toList();
        return new ResponseEntity<>(listadoCursos, HttpStatus.OK);
    }

    @GetMapping("/listarCursoPorId/{idCourse}")
    public ResponseEntity<CursoDTO> listadoPorId(@PathVariable("idCourse") Integer idCourse) {
        Curso course = cursoService.findById(idCourse);
        return new ResponseEntity<>(mapper.map(course, CursoDTO.class), HttpStatus.OK);
    }

    @PostMapping("/createCourseRegistration")
    public ResponseEntity<CursoDTO> saveCourseRecord(@Valid @RequestBody CursoDTO courseDTO) {
        Curso courseDetails = cursoService.create(mapper.map(courseDTO, Curso.class));
        return new ResponseEntity<>(mapper.map(courseDetails, CursoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/updateCourseRecord/{idCourse}")
    public ResponseEntity<CursoDTO> updateCourseRecord(@Valid @RequestBody CursoDTO courseDTO, @PathVariable("idCourse") Integer idCourse) throws Exception {
        Curso courseUpdate = cursoService.update(mapper.map(courseDTO, Curso.class), idCourse);
        return new ResponseEntity<>(mapper.map(courseUpdate, CursoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/courseRecordDeletion/{idCourse}")
    public ResponseEntity<Void> deleteCourseRecord(@PathVariable("idCourse") Integer idCourse) {
        cursoService.delete(idCourse);
        return ResponseEntity.noContent().build();
    }
}
