package com.app.rest.model.dto;


import com.app.rest.format.DateFormat;
import com.app.rest.model.persistence.OrderDetail;

import java.util.Optional;

public class OrderDTO {

    private Long id;
    private String code;
    private String date;
    private OrderStatus status;

    private OrderDTO(Long id, String code, String date, OrderStatus status) {
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

    public String getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
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
}
