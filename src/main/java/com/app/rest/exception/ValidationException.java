package com.app.rest.exception;

public class ValidationException extends Exception {

    public ValidationException(){};

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable t) {
        super(message, t);
    }

    public ValidationException(Throwable t) {
        super(t);
    }
    
}
