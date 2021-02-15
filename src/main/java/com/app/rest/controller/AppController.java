package com.app.rest.controller;

import com.app.rest.model.Pedido;
import com.app.rest.service.ServicioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
class AppController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @Autowired
    ServicioPedido pedido;

    //Devuelve todos los pedidos
    @GetMapping("/pedidos")
    public ResponseEntity<Object> pedidos(){
        return new ResponseEntity<Object>(pedido.getPedidos(), HttpStatus.OK);
    }
    
}