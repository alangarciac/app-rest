package com.app.rest.exception.itemExceptions;

public class ItemPersistanceException extends Exception {

    public ItemPersistanceException(){};

    public ItemPersistanceException(String message) {
        super(message);
    }

    public ItemPersistanceException(String message, Throwable t) {
        super(message, t);
    }

    public ItemPersistanceException(Throwable t) {
        super(t);
    }
    
}
