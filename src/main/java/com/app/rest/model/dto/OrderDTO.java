package com.app.rest.model.dto;


import com.app.rest.exception.itemExceptions.ItemTypeException;
import com.app.rest.format.DateFormat;
import com.app.rest.model.persistence.OrderDetail;
import com.app.rest.model.persistence.UserDetail;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderDTO implements Checkable {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDTO.class);

    private Long id;
    private String code;
    private LocalDateTime date;
    private OrderStatus status;

    private List<ItemDTO> items;
    private List<String> itemsIds;

    // TODO must be a UserDTO
    private UserDetail user;
    private String userId;

    private OrderDTO(Long id, String code, LocalDateTime date, OrderStatus status, List<ItemDTO> items) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.status = status;
        this.items = items;
    }

    @JsonCreator
    public OrderDTO(List<String> itemsIds, String userId) {
        this.date = LocalDateTime.now();
        this.itemsIds = itemsIds;
        this.userId = userId;
    }

    public OrderDTO(OrderDetail orderDetail) throws IllegalStateException {
        this(
                orderDetail.getId(),
                orderDetail.getCode(),
                orderDetail.getDate(),
                Optional.ofNullable(OrderStatus.fromString(orderDetail.getStatus()))
                .orElseThrow(
                        () -> new IllegalStateException(String.format("Invalid status [%s]", orderDetail.getStatus()))
                ),
                orderDetail.getItems().stream().map(itemDetail -> {
                    try {
                        return new ItemDTO(itemDetail);
                    } catch (ItemTypeException e) {
                        LOGGER.error("Error trying retrieve type of item {}", itemDetail);
                    }
                    return null;
                }).collect(Collectors.toList())
        );
    }

    @JsonIgnore
    public Long id(){
        return id;
    }

    @JsonIgnore
    public LocalDateTime getLocalDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) { this.code = code; }

    public void setStatus(OrderStatus status) { this.status = status; }

    public String getDate() {
        return DateFormat.printBeauty(date);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<ItemDTO> getItems() { return items; }

    public void setItems(List<ItemDTO> items) { this.items = items; }

    public UserDetail getUser() {
        return user;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @JsonIgnore
    @Override
    public boolean isSupported() {
        // TODO not implemented yet
        return true;
    }

    @Override
    public void validate() {
        // TODO not implemented yet
        throw new NotImplementedException();
    }
}
