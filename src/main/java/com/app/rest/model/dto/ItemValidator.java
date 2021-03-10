package com.app.rest.model.dto;

import com.app.rest.exception.itemExceptions.ItemTypeException;
import com.app.rest.exception.itemExceptions.ItemValidateException;

public interface ItemValidator {
    void validate() throws ItemValidateException, ItemTypeException;
}
