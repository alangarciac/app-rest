package com.app.rest.exception;

public class ItemNotFoundException extends OrderException{

    public ItemNotFoundException() {}

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public ItemNotFoundException(Throwable t) {
        super(t);
    }
}
