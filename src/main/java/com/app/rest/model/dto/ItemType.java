package com.app.rest.model.dto;

public enum ItemType {

    VEGAN("VEGAN"),
    NOVEGAN("NOVEGAN"),
    OKCELIAC("OKCELIAC");

    private String value;

    ItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ItemType fromString(String itemtype) {
        for (ItemType itemType : ItemType.values()) {
            if (itemType.getValue().equalsIgnoreCase(itemtype)) {
                return itemType;
            }
        }
        return null;
    }

}
