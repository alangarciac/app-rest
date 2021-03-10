package com.app.rest.model.dto;

public interface Checkable {

    boolean isSupported();
    void validate() throws Exception;


}
