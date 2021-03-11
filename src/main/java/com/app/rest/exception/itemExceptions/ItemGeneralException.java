package com.app.rest.exception.itemExceptions;

public class ItemGeneralException extends ItemException {

    public ItemGeneralException(){};

    public ItemGeneralException(String message) {
        super(message);
    }

    public ItemGeneralException(String message, Throwable t) {
        super(message, t);
    }

    public ItemGeneralException(Throwable t) {
        super(t);
    }
    
}
