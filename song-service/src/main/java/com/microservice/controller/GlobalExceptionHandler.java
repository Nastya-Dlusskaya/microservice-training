package com.microservice.controller;

import com.microservice.exception.BadRequestException;
import com.microservice.exception.DuplicationException;
import com.microservice.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
        return createErrorResponse(ex.getMessage(), "400", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicationException(DuplicationException ex) {
        return createErrorResponse(ex.getMessage(), "409", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex) {
        return createErrorResponse(ex.getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(NoSuchElementException ex) {
        return createErrorResponse(ex.getMessage(), "404", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse;
        if (ex.getPropertyName().equals("id")) {
            return createErrorResponse(String.format("Invalid value '%s' for ID. Must be a positive integer",
                    ex.getValue()), "400", HttpStatus.BAD_REQUEST);
        } else {
            return createErrorResponse(ex.getMessage(), "400", HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });
        return createErrorsResponse(errors, "400", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponse> createErrorResponse(String message, String code, HttpStatus status) {
        ExceptionResponse errorResponse = new ExceptionResponse(message, code);
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<List<ExceptionResponse>> createErrorsResponse(List<String> messages, String code, HttpStatus status) {
        List<ExceptionResponse> errors = messages.stream().map(error -> new ExceptionResponse(error, code)).toList();
        return ResponseEntity.status(status).body(errors);
    }
}
