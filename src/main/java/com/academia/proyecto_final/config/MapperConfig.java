package com.academia.proyecto_final.config;

import com.academia.proyecto_final.dto.EstudianteDTO;
import com.academia.proyecto_final.model.Estudiante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper estudianteMapper(){

        ModelMapper mapperPro = new ModelMapper();
        mapperPro.createTypeMap(Estudiante.class, EstudianteDTO.class)
                .addMapping(Estudiante::getNombres, EstudianteDTO::setNombresEstudiante);

        mapperPro.createTypeMap(EstudianteDTO.class, Estudiante.class)
                .addMapping(EstudianteDTO::getNombresEstudiante, Estudiante::setNombres);

        return mapperPro;
    }



}
