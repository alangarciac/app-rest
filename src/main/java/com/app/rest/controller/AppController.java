package com.app.rest.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
class AppController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/all")
    List<String> all() {
        // ret element from database or remote service
        return Collections.emptyList();
    }

    @PostMapping("/post")
    String newElement(@RequestBody String newElement) {
        // example: return repository.save(newElement);
        return null;
    }

}