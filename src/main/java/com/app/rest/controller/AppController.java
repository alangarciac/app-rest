package com.app.rest.controller;

import org.springframework.web.bind.annotation.*;


import com.app.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
class AppController {

    @RequestMapping("/")
    public String index() {
        return "WELCOME";
    }

}
