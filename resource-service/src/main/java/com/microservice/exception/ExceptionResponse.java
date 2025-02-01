package com.microservice.exception;

public class ExceptionResponse {
    private String message;
    private String code;

    public ExceptionResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
