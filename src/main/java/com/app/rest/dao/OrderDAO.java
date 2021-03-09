package com.app.rest.dao;

import com.app.rest.exception.OrderNotFoundException;
import com.app.rest.exception.OrderPersistenceException;
import com.app.rest.format.DateFormat;
import com.app.rest.model.dto.OrderDTO;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.model.persistence.OrderDetail;
import com.app.rest.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderDAO {

    @Autowired
    private OrderRepo orderRepo;

    public OrderDTO retrieveOrderById(Long id) throws OrderNotFoundException, IllegalStateException {
        OrderDetail orderDetail = Optional.ofNullable(orderRepo.findById(id)).get()
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id[%s] not found.", id)));
        return new OrderDTO(orderDetail);
    }

    public Long createNewOrder(OrderDTO order) throws OrderPersistenceException {
        OrderDetail orderDetail = new OrderDetail(
                order.getCode(),
                order.getLocalDate(),
                order.getStatus().getValue(),
                order.getItems().stream().map(
                        itemDTO -> new ItemDetail(itemDTO.getName(),
                                itemDTO.getType(),
                                itemDTO.getDescription(),
                                itemDTO.isDeleted(),
                                DateFormat.toEpoch(itemDTO.getLastModifiedDate())))
                        .collect(Collectors.toList()),
                order.getUser()
        );
        return Optional.ofNullable(orderRepo.save(orderDetail).getId()).orElseThrow(OrderPersistenceException::new);
    }
}
