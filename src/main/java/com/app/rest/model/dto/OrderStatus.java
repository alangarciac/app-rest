package com.app.rest.model.dto;

public enum OrderStatus {

    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    DELAYED("DELAYED"),
    DONE("DONE");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatus fromString(String order) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getValue().equalsIgnoreCase(order)) {
                return orderStatus;
            }
        }
        return null;
    }

}
