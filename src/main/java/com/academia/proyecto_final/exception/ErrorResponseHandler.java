package com.academia.proyecto_final.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseHandler {

    private int status;
    private LocalDateTime timestamp;
    private String description;
    private String path;

}
