package com.app.rest.service;

import com.app.rest.exception.OrderNotFoundException;
import com.app.rest.exception.OrderPersistenceException;
import com.app.rest.model.dto.OrderDTO;
import com.app.rest.dao.OrderDAO;
import com.app.rest.model.dto.OrderStatus;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDAO orderDAO;

    // TODO in progress
    public List<OrderDTO> getOrders() {
        throw new NotImplementedException();
    }

    public OrderDTO findOrderById(Long id) throws OrderNotFoundException, IllegalStateException {
        try{
            return orderDAO.retrieveOrderById(id);
        } catch (OrderNotFoundException | IllegalStateException e){
            LOGGER.error("Error trying retrieve order with id {}. Caused by[{}]", id, e.getMessage());
            throw e;
        }
    }

    public Long createNewOrder(OrderDTO order) throws OrderPersistenceException {
        try {
            order.setCode(UUID.randomUUID().toString());
            order.setStatus(OrderStatus.PENDING);
            // TODO obtain Items from ItemIds and set to OrderDTO
            order.setItems(Arrays.asList());
            // TODO obtain User from UserId and set to OrderDTO
            return orderDAO.createNewOrder(order);
        } catch (OrderPersistenceException e) {
            LOGGER.error("Some error was present during save process of Order");
            throw e;
        }
    }

}
