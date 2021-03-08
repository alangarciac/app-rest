package com.app.rest.model.dto;


import com.app.rest.format.DateFormat;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.model.persistence.OrderDetail;
import com.app.rest.model.persistence.UserDetail;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderDTO {

    private Long id;
    private String code;
    private String date;
    private OrderStatus status;

    // TODO must be a List of Item
    private List<ItemDetail> items;

    // TODO must be a User
    private UserDetail user;

    private Order(Long id, String code, String date, OrderStatus status) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.status = status;
    }

    public OrderDTO(OrderDetail orderDetail) throws IllegalStateException {
        this(
                orderDetail.getId(),
                orderDetail.getCode(),
                DateFormat.printBeauty(orderDetail.getDate()),
                Optional.ofNullable(OrderStatus.fromString(orderDetail.getStatus()))
                .orElseThrow(
                        () -> new IllegalStateException(String.format("Invalid status [%s]", orderDetail.getStatus()))
                )
        );
    }

    public Long id(){
        return id;
    }

    public String getCode() {
        return code;
    }

    public String beautyDate() {
        return date;
    }

    public Date getDate() {
        return null;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<ItemDetail> getItems() {
        return items;
    }

    public UserDetail getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}
