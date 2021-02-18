package com.app.rest.controller;

import com.app.rest.model.Pedido;
import com.app.rest.service.ServicioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;

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
    ServicioPedido pedido;

    //Devuelve todos los pedidos
    @GetMapping("/pedidos")
    public ResponseEntity<Object> pedidos(){
        try {
            return new ResponseEntity<Object>(pedido.getPedidos(), HttpStatus.OK);
        } catch (InterruptedException e) {
            return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Devuelve el pedido por id
    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Object> getPedido(@PathVariable int id) {
        return new ResponseEntity<Object>(pedido.getPedido(id), HttpStatus.OK);

    }

    //Borra un pedido por id
    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable int id) {
        return new ResponseEntity<Object>(pedido.deletePedido(id), HttpStatus.OK);
    }

    //Crea un nuevo pedido
    @PostMapping("/pedidos/nuevo")
    public ResponseEntity<Object> pedido(@RequestBody ServicioPedido newServicio) {
        return new ResponseEntity<Object>(pedido.newPedido(newServicio),HttpStatus.OK);
    }
}