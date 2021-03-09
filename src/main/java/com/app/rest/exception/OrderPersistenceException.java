package com.app.rest.exception;

public class OrderPersistenceException extends OrderException {

    public OrderPersistenceException() {}

    public OrderPersistenceException(String message) {
        super(message);
    }

    public OrderPersistenceException(String message, Throwable t) {
        super(message, t);
    }

    public OrderPersistenceException(Throwable t) {
        super(t);
    }

}
