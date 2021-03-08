package com.app.rest.controller;

import com.app.rest.exception.ItemException;
import com.app.rest.exception.ItemNotFoundException;
import com.app.rest.model.dto.ItemDTO;
import com.app.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDTO> retrieveItem(@PathVariable("id") Long id) {
        try{
            ItemDTO itemDTO = itemService.getItemById(Optional.ofNullable(id).orElseThrow(IllegalStateException::new));
            return ResponseEntity.ok(itemDTO);
        } catch (IllegalStateException ie) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (ItemNotFoundException | ItemException nf) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> retrieveItems() {
        try{
            List<ItemDTO> itemsDTO = itemService.getItems();
            return ResponseEntity.ok(itemsDTO);
        } catch (IllegalStateException | ItemException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping("/items/new")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        try {
            itemDTO.validate();   //Debo validar porque el DTO se autocarga con el json usando reflexion y pueden qdar campos nulos.
            itemDTO = itemService.saveItem(itemDTO);
            return ResponseEntity.ok(itemDTO);
        } catch (IllegalArgumentException is) {
            return ResponseEntity.badRequest().body(is.getMessage());
        } catch (ItemException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
        try {
            ItemDTO itemDTO = itemService.deleteItem(Optional.ofNullable(id).orElseThrow(IllegalStateException::new));
            return ResponseEntity.ok(itemDTO);
        } catch (ItemNotFoundException nf) {
            return ResponseEntity.notFound().build();
        } catch (ItemException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable("id") Long id, @RequestBody ItemDTO itemDTO) {
        try {
            itemDTO.validate();   //Debo validar porque el DTO se autocarga con el json usando reflexion y pueden qdar campos nulos.
            itemDTO = itemService.updateItem(id, itemDTO);
            return ResponseEntity.ok(itemDTO);
        } catch (ItemNotFoundException nf) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException is) {
            return ResponseEntity.badRequest().body(is.getMessage());
        } catch (ItemException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
