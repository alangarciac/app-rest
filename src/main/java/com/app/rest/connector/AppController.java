package com.app.rest.connector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
class AppController {

    @GetMapping("/all")
    List<String> all() {
        // ret element from database or remote service
        return Collections.emptyList();
    }

    @PostMapping("/all")
    String newElement(@RequestBody String newElement) {
        // example: return repository.save(newElement);
        return null;
    }

}