package com.app.rest.exception.itemExceptions;

public class ItemPersistenceException extends ItemException {

    public ItemPersistenceException(){}

    public ItemPersistenceException(String message) {
        super(message);
    }

    public ItemPersistenceException(String message, Throwable t) {
        super(message, t);
    }

    public ItemPersistenceException(Throwable t) {
        super(t);
    }
    
}
