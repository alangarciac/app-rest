package com.app.rest.exception.itemExceptions;

import com.app.rest.exception.OrderException;

public class ItemNotFoundException extends OrderException {

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
