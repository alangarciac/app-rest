package com.app.rest.model.dto;

import com.app.rest.exception.ItemTypeException;
import com.app.rest.exception.ItemValidateException;
import com.app.rest.model.persistence.ItemDetail;
import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class ItemDTO {

    private Long id;
    private String name;
    private ItemType type;
    private String description;
    private boolean deleted;
    private Date last_modified;

    public ItemDTO(String name, String type, String description, boolean deleted, Long last_modified) throws ItemTypeException {
        this.name = name;
        this.type = ItemType.fromString(type);
        this.description = description;
        this.deleted = deleted;
        this.last_modified = new Date(last_modified);
    }
    public ItemDTO(ItemDetail itemDetail) throws ItemTypeException {
        this.id = itemDetail.getId();
        this.name = itemDetail.getName();
        this.description = itemDetail.getDescription();
        this.deleted = itemDetail.isDeleted();
        this.type = ItemType.fromString(itemDetail.getType());
        this.last_modified = new Date(itemDetail.getLast_modified());
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

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Long last_modified) {
        this.last_modified = new Date(last_modified);
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                ", last_modified=" + last_modified +
                '}';
    }

    public void validate () throws ItemValidateException{
        if (!StringUtils.hasText(name) || !StringUtils.hasText(description)){ //type boolean cant be null, when null->false, which is ok here.
            throw new ItemValidateException(MessageFormat.format("Object Missing Fields - current values {0},{1},{2},{3}", this.name, this.description, this.type, this.deleted));
        }
    }
}
