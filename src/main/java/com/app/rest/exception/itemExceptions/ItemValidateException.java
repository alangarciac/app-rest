package com.app.rest.exception.itemExceptions;

public class ItemValidateException extends Exception {

    public ItemValidateException(){};

    public ItemValidateException(String message) {
        super(message);
    }

    public ItemValidateException(String message, Throwable t) {
        super(message, t);
    }

    public ItemValidateException(Throwable t) {
        super(t);
    }
    
}
