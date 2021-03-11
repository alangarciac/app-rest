package com.app.rest.exception.itemExceptions;

public class ItemException extends Exception {

    public ItemException(){};

    public ItemException(String message) {
        super(message);
    }

    public ItemException(String message, Throwable t) {
        super(message, t);
    }

    public ItemException(Throwable t) {
        super(t);
    }
    
}
