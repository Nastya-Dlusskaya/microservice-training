package com.microservice.controller;

import com.microservice.exception.ExceptionResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
        return createErrorResponse(ex.getMessage(), "400", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        return createErrorResponse("Unsupported media type. Received Content-Type: "
                + (ex.getContentType() != null ? ex.getContentType().toString() : "undefined")
                + ". Accepted format: 'audio/mpeg'", "400", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return createErrorResponse(ex.getMessage(), "404", HttpStatus.NOT_FOUND);
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

    private ResponseEntity<ExceptionResponse> createErrorResponse(String message, String code, HttpStatus status) {
        ExceptionResponse errorResponse = new ExceptionResponse(message, code);
        return ResponseEntity.status(status).body(errorResponse);
    }
}
