package com.example.onlineshop.exception.runtimeExceptions;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException{
    private final String field;
    public MyException(String message, String field) {
        super(message);
        this.field = field;
    }
}
