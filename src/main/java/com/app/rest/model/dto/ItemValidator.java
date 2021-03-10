package com.app.rest.model.dto;

import com.app.rest.exception.ItemValidateException;

public interface ItemValidator {
    void validate() throws ItemValidateException;
}
