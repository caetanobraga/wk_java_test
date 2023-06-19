package com.wk.java_test.ApiExceptionHandler;

import com.wk.java_test.ApiExceptionHandler.exceptions.InvalidFileException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalTime.now;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Map<String, Object>> handlePostNotFoundException(InvalidFileException ex,
                                                                           HttpServletRequest request) {
        HttpStatus status = HttpStatus.PARTIAL_CONTENT;
        String mensagem = ex.getMessage();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", mensagem);
        body.put("path", request.getServletPath());

        return new ResponseEntity<>(body, status);
    }
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
