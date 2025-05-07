package com.dr7.dr7.infra.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class ExeptionValidation {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<validationDto> ValidaExeption(RuntimeException e){
        return ResponseEntity.badRequest().body(new validationDto(e.getMessage()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("erro", "Usuário ou senha inválidos"));
    }
    @ExceptionHandler(IOException.class)
    public  ResponseEntity<?> responseEntity(IOException e){
        return ResponseEntity.status(500).body(Map.of("erro", e.getMessage()));
    }
}
