package com.example.onlineshop.exception.handler;

import com.example.onlineshop.dto.APIErrorResponse;
import com.example.onlineshop.exception.EmailAlreadyExists;
import com.example.onlineshop.exception.UsernameAlreadyExists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/** ExceptionHandler klass on exceptioni handler tagastamaks veateateid
 *
 */@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        /**
         * APIErrorResponse on tagastus kliendile
         */
        APIErrorResponse response = APIErrorResponse.validationException(errors, request.getDescription(false));
        return ResponseEntity.badRequest().body(response);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<APIErrorResponse> handleUsernameAlreadyExists(UsernameAlreadyExists ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("username", ex.getMessage());

        APIErrorResponse response = APIErrorResponse.validationException(errors, request.getDescription(false));
        return ResponseEntity.badRequest().body(response);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<APIErrorResponse> handleEmailAlreadyExists(EmailAlreadyExists ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("email", ex.getMessage());

        APIErrorResponse response = APIErrorResponse.validationException(errors, request.getDescription(false));
        return ResponseEntity.badRequest().body(response);
    }
}

