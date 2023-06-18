package com.wk.java_test.ApiExceptionHandler;

import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Optional;

@ControllerAdvice
public class ApiExceptionHandler {

    private String extractError(MethodArgumentNotValidException ex) {

        Optional<ObjectError> erroOpt = ex.getBindingResult().getAllErrors().stream()
                .findFirst();

        if (erroOpt.isEmpty()) {
            return "erro de validação";
        }

        FieldError error = (FieldError) erroOpt.get();

        return error.getField() + ": " + error.getDefaultMessage();
    }
}
