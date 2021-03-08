package com.app.rest.exception;

public class ItemTypeException extends Exception {

    public ItemTypeException(){};

    public ItemTypeException(String message) {
        super(message);
    }

    public ItemTypeException(String message, Throwable t) {
        super(message, t);
    }

    public ItemTypeException(Throwable t) {
        super(t);
    }
    
}
