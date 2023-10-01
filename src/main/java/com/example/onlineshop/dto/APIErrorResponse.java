package com.example.onlineshop.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Accessors(chain = true)
public class APIErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Map<String, String> errors;
    private String path;

    public static APIErrorResponse validationException(Map<String, String> errors, String path) {
        return new APIErrorResponse()
                .setTimestamp(LocalDateTime.now())
                .setMessage("Validation error")
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setErrors(errors)
                .setPath(path);
    }
}
