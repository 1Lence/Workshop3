package org.example.synthetichumancorestarter.androidModule.controller.exceptionHandler;

import org.example.synthetichumancorestarter.androidModule.exception.ThreadFoolException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalAndroidExceptionHandler {

    @ExceptionHandler(ThreadFoolException.class)
    public ResponseEntity<?> handleMaxThreadPoolException(ThreadFoolException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleAndroidCorrectnessException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<?> handleAndroidPriorityInput(HttpMessageConversionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}