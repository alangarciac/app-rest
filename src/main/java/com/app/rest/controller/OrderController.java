package com.app.rest.controller;

import com.app.rest.exception.OrderNotFoundException;
import com.app.rest.model.dto.OrderDTO;
import com.app.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> retrieveOrder(@PathVariable("id") Long id) {
        try{
            OrderDTO orderDTO = orderService.findOrderById(Optional.ofNullable(id).orElseThrow(IllegalStateException::new));
            return ResponseEntity.ok(orderDTO);
        } catch (IllegalStateException ie) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (OrderNotFoundException nf) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Long> createOrder(@RequestBody Order order) {
        try{
            if(order.isSupported()) {
                return new ResponseEntity<Long>(orderService.createNewOrder(order), HttpStatus.CREATED);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (IllegalStateException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
