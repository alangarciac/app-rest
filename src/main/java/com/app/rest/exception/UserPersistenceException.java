package com.app.rest.exception;

public class UserPersistenceException extends UserException {

    public UserPersistenceException() {}

    public UserPersistenceException(String message) {
        super(message);
    }

    public UserPersistenceException(String message, Throwable t) {
        super(message, t);
    }

    public UserPersistenceException(Throwable t) {
        super(t);
    }

}
