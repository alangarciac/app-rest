package com.app.rest.exception;

public class OrderException extends Exception {

    public OrderException(){};

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable t) {
        super(message, t);
    }

    public OrderException(Throwable t) {
        super(t);
    }
    
}
