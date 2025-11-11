package com.aronque.foursales.controllers;

import com.aronque.foursales.entities.dto.DefaultMessageDTO;
import com.aronque.foursales.exceptions.InvalidAttributeException;
import com.aronque.foursales.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound(ResourceNotFoundException ex) {
        DefaultMessageDTO error = new DefaultMessageDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(InvalidAttributeException.class)
    public ResponseEntity handleInvalidAttribute(InvalidAttributeException ex) {
        DefaultMessageDTO error = new DefaultMessageDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleInvalidInput(MethodArgumentNotValidException ex) {
        DefaultMessageDTO error = new DefaultMessageDTO(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleInvalidCredentials(BadCredentialsException ex) {
        DefaultMessageDTO error = new DefaultMessageDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
