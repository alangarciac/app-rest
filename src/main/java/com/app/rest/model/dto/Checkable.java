package com.app.rest.model.dto;

import com.app.rest.exception.ValidationException;

public interface Checkable {

    void validate() throws ValidationException;


}
