package com.app.rest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
class AppController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    }
    