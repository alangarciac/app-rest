package com.app.rest.model.dto;

import com.app.rest.exception.ItemTypeException;

import java.util.Arrays;

public enum ItemType {

    FOOD("FOOD"),
    SERVICE("SERVICE"),
    OTHER("OTHER");

    private String value;

    ItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ItemType fromString(String itemtype) throws ItemTypeException {
        for (ItemType itemType : values()) {
            if (itemType.getValue().equalsIgnoreCase(itemtype)) {
                return itemType;
            }
        }
        throw new ItemTypeException("Item Type not valid, types admitted:" + Arrays.toString(values()));
    }
}
