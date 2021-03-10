package com.app.rest.model.dto;

import com.app.rest.exception.ItemTypeException;
import com.app.rest.exception.ItemValidateException;
import com.app.rest.format.DateFormat;
import com.app.rest.model.persistence.ItemDetail;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.StringUtils;
import java.text.MessageFormat;
import java.time.LocalDateTime;

public class ItemDTO implements ItemValidator {

    private Long id;
    private String name;
    private ItemType type;
    private String description;
    private boolean deleted;
    private LocalDateTime lastModified;

    /*public ItemDTO(String name, String type, String description, boolean deleted, Long lastModified) throws ItemTypeException {
        this.name = name;
        this.type = ItemType.fromString(type);
        this.description = description;
        this.deleted = deleted;
        this.lastModified = DateFormat.fromEpoch(lastModified);
    }*/

    @JsonCreator //Constructor for Jackson, assumes some values
    public ItemDTO(String name, String type, String description) throws ItemTypeException {
        this.name = name;
        this.type = ItemType.fromString(type);
        this.description = description;
        this.deleted = false;
        this.lastModified = DateFormat.now();
    }

    public ItemDTO(ItemDetail itemDetail) throws ItemTypeException {
        this.id = itemDetail.getId();
        this.name = itemDetail.getName();
        this.description = itemDetail.getDescription();
        this.deleted = itemDetail.isDeleted();
        this.type = ItemType.fromString(itemDetail.getType());
        this.lastModified = DateFormat.fromEpoch(itemDetail.getLast_modified());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type.toString();
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getLastModified() { return DateFormat.printBeauty(this.lastModified); }

    @JsonIgnore
    public LocalDateTime getLastModifiedDate() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = DateFormat.fromEpoch(lastModified);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public void validate () throws ItemValidateException{
        if (!StringUtils.hasText(name) || !StringUtils.hasText(description)){ //type boolean cant be null, when null->false, which is ok here.
            throw new ItemValidateException(MessageFormat.format("Object Missing Fields - current values {0},{1},{2},{3}", this.name, this.description, this.type, this.deleted));
        }
    }
}
