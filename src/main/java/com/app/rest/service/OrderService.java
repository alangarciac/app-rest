package com.app.rest.service;

import com.app.rest.model.Item;
import com.app.rest.model.OrderDetail;
import com.app.rest.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public List<OrderDetail> getOrders() throws InterruptedException {
        return orderRepo.findAll();
    }
    public Optional<OrderDetail> getOrder(Long id) throws InterruptedException {
        return orderRepo.findById(id);
    }
    public Long saveOrder(OrderDetail order) throws InterruptedException {
        return orderRepo.save(order).getId();
    }

}
