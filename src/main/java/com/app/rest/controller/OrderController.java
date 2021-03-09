package com.app.rest.controller;

import com.app.rest.exception.OrderNotFoundException;
import com.app.rest.exception.OrderPersistenceException;
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
    public ResponseEntity<OrderDTO> retrieveOrder(@PathVariable("id") Long id) {
        try{
            OrderDTO orderDTO = orderService.findOrderById(Optional.ofNullable(id).orElseThrow(IllegalStateException::new));
            return ResponseEntity.ok(orderDTO);
        } catch (IllegalStateException ie) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (OrderNotFoundException nf) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders/new")
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTO order) {
        try{
            return order.isSupported() ?
                    new ResponseEntity<Long>(orderService.createNewOrder(order), HttpStatus.CREATED) : ResponseEntity.badRequest().build();
        } catch (OrderPersistenceException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
