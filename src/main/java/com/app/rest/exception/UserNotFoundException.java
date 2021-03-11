package com.app.rest.exception;

public class UserNotFoundException extends UserException{

    public UserNotFoundException() {}

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public UserNotFoundException(Throwable t) {
        super(t);
    }
}
