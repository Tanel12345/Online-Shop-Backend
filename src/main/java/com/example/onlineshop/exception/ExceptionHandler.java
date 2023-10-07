package com.example.onlineshop.exception;

import com.example.onlineshop.dto.APIErrorResponse;
import com.example.onlineshop.exception.runtimeExceptions.*;
import org.modelmapper.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<APIErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        APIErrorResponse response = APIErrorResponse.validationException(errors, request.getDescription(false));
        return ResponseEntity.badRequest().body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({MyException.class})
    public ResponseEntity<APIErrorResponse> handleMyException(MyException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getField(), ex.getMessage());
        APIErrorResponse response = APIErrorResponse.validationException(errors, request.getDescription(false));
        return ResponseEntity.badRequest().body(response);
    }
}

