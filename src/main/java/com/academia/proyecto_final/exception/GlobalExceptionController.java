package com.academia.proyecto_final.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(EstudianteNotFoundException.class)
    public ResponseEntity<ErrorResponseHandler> EstudianteHandlerException(EstudianteNotFoundException ex, WebRequest request){

        ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseHandler);
    }

    @ExceptionHandler(CursoNotFoundException.class)
    public ResponseEntity<ErrorResponseHandler> CursoHandlerException(CursoNotFoundException ex, WebRequest request){

        ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseHandler);
    }



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseHandler> resourceNotFoundHandlerException(
            ResourceNotFoundException ex, WebRequest request) {

        ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseHandler);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseHandler> dataIntegrityViolationHandlerException(
            DataIntegrityViolationException ex, WebRequest request) {

        ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                "The requested operation conflicts with existing related records.",
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseHandler);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseHandler> cursoHandlerException(Exception ex, WebRequest request){

        ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseHandler);
    }
}
