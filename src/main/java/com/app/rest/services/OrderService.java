package com.app.rest.services;

import com.app.rest.model.OrderDetail;
import com.app.rest.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public List<OrderDetail> getOrders() throws InterruptedException {
        return orderRepo.findAll();
    }
}
