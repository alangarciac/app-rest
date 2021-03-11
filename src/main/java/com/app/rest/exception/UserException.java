package com.app.rest.exception;

public class UserException extends Exception {

    public UserException(){};

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable t) {
        super(message, t);
    }

    public UserException(Throwable t) {
        super(t);
    }
    
}
