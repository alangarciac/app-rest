package com.app.rest.controller;

import com.app.rest.model.dto.ItemDTO;
import com.app.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
        } catch (NoSuchElementException nf) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> retrieveItems() {
        try{
            List<ItemDTO> itemsDTO = itemService.getItems();
            return ResponseEntity.ok(itemsDTO);
        } catch (IllegalStateException ie) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (DataAccessException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping("/items/new")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        try {
            itemDTO.validate();   //Validacion de logica en el DTO.
            itemDTO = itemService.saveItem(itemDTO);
            return ResponseEntity.ok(itemDTO);
        } catch (IllegalArgumentException is) {
            return ResponseEntity.badRequest().body(is.getMessage());
        } catch (DataAccessException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
        try {
            ItemDTO itemDTO = itemService.deleteItem(Optional.ofNullable(id).orElseThrow(IllegalStateException::new));
            return ResponseEntity.ok(itemDTO);
        } catch (NoSuchElementException nf) {
            return ResponseEntity.notFound().build();
        } catch (DataAccessException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
