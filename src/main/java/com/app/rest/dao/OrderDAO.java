package com.app.rest.dao;

import com.app.rest.exception.OrderNotFoundException;
import com.app.rest.model.dto.Order;
import com.app.rest.model.persistence.OrderDetail;
import com.app.rest.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class OrderDAO {

    @Autowired
    private OrderRepo orderRepo;

    public Order retrieveOrderById(Long id) throws OrderNotFoundException, IllegalStateException {
        OrderDetail orderDetail = Optional.ofNullable(orderRepo.findById(id)).get()
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id[%s] not found.", id)));
        return new Order(orderDetail);
    }

    public Long createNewOrder(Order order) {
        return Optional.ofNullable(orderRepo.save(new OrderDetail(order)).getId()).orElseThrow(IllegalStateException::new);
    }
}
