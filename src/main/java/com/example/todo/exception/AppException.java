package com.example.todo.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    public final HttpStatus status;

    public AppException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
