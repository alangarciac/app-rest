package com.app.rest.service;

import com.app.rest.exception.OrderNotFoundException;
import com.app.rest.model.dto.OrderDTO;
import com.app.rest.dao.OrderDAO;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    // TODO in progress
    public Long createNewOrder(OrderDTO order) {
        return orderDAO.createNewOrder(order);
    }

}
