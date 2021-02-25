package com.app.rest.exception;

public class OrderNotFoundException extends OrderException{

    public OrderNotFoundException() {}

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public OrderNotFoundException(Throwable t) {
        super(t);
    }
}
