package com.app.rest.model.dto;


import com.app.rest.exception.itemExceptions.ItemTypeException;
import com.app.rest.exception.ValidationException;
import com.app.rest.format.DateFormat;
import com.app.rest.model.persistence.UserDetail;
import com.app.rest.model.persistence.UserDetail;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UserDTO implements Checkable {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDTO.class);

    private Long id;
    private String name;
    private String email;
    private boolean deleted;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private UserDTO(Long id, String name, String email, boolean deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.deleted = deleted;
    }

    @JsonCreator
    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.deleted = false;
    }

    public UserDTO(UserDetail userDetail) throws IllegalStateException {
        this(
                userDetail.getId(),
                userDetail.getName(),
                userDetail.getEmail(),
                userDetail.isDeleted()
        );
    }

    @JsonIgnore
    public Long id(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


    @Override
    public void validate() {
        // TODO not implemented yet
        throw new NotImplementedException();
    }

    @JsonIgnore
    public boolean isSupported() {
        // TODO not implemented yet
        return true;
    }
}
