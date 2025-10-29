package com.example.todo.config;

import com.example.todo.exception.AppException;
import com.example.todo.utils.CookieUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class AppExceptionHandler  {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<String> handleAppException(AppException exception, HttpServletResponse response) {
        if (exception.status == HttpStatus.UNAUTHORIZED) {
            CookieUtils.attachDeleteCookieHeader(response);
        }

        return ResponseEntity
                .status(exception.status)
                .body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(exception.getMessage());
    }
}
