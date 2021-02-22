package com.app.rest.controller;

import com.app.rest.model.Item;
import com.app.rest.services.ItemService;
import com.app.rest.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class AppController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @Autowired
    OrderService order;
    @Autowired
    ItemService item;


    //Returns all orders
    @GetMapping("/orders")
    public ResponseEntity<Object> orders(){
        try {
            item.save(new Item("verga","verga","verga"));
            return new ResponseEntity<Object>(order.getOrders(), HttpStatus.OK);
        } catch (InterruptedException e) {
            return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}