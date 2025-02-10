package com.microservice.exception;

public class DuplicationException extends Throwable {
    public DuplicationException(String message) {
        super(message);
    }
}
