package com.app.rest.exception.itemExceptions;

public class ItemTypeException extends ItemException {

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
