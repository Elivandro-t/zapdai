package com.dr7.dr7.infra.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExeptionValidation {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<validationDto> ValidaExeption(RuntimeException e){
        return ResponseEntity.badRequest().body(new validationDto(e.getMessage()));
    }
}
