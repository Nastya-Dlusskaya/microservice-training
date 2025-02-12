package com.microservice.exception;

public class DuplicationException extends Exception {
    public DuplicationException(String message) {
        super(message);
    }
}
