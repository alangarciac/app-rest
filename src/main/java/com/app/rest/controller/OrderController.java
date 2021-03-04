package com.app.rest.controller;

import com.app.rest.exception.OrderNotFoundException;
import com.app.rest.model.dto.OrderDTO;
import com.app.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id}")
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

}
