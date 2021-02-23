package com.app.rest.controller;


import org.springframework.web.bind.annotation.*;


import com.app.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



/**
    ToDo: Revisar los codigos de error en los catch
**/


@RestController
class AppController {

    @RequestMapping("/")
    public String index() {
        return "Nothing to see here";
    }

    @Autowired
    OrderService order;

    //Returns all orders
    @GetMapping("/orders")
    public ResponseEntity<Object> orders(){
        try {
            return new ResponseEntity<Object>(order.getOrders(), HttpStatus.OK);
        } catch (InterruptedException e) {
            return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Returns an order by id
    //y las excepciones?
    /*
    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable int id) {
        return new ResponseEntity<Object>(order.getOrder(id), HttpStatus.OK);

    }*/

    //Deletes an order by id
    //y las excepciones?
    /*@DeleteMapping("/orders/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable int id) {
        return new ResponseEntity<Object>(order.deleteOrder(id), HttpStatus.OK);
    }*/

    //Creates a new order - WRONG
    /*@PostMapping("/orders/new")
    public ResponseEntity<Object> order(@RequestBody OrderService newService) {
        return new ResponseEntity<Object>(order.newOrder(newService),HttpStatus.OK);
    }*/
}
