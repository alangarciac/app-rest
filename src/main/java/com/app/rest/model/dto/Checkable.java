package com.app.rest.model.dto;

import com.app.rest.exception.itemExceptions.ItemValidateException;

public interface Checkable {

    boolean isSupported();
    void validate() throws Exception;


}
