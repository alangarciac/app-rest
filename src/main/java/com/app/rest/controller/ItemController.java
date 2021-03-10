package com.app.rest.controller;

import com.app.rest.exception.itemExceptions.ItemException;
import com.app.rest.exception.itemExceptions.ItemNotFoundException;
import com.app.rest.exception.itemExceptions.ItemValidateException;
import com.app.rest.model.dto.ItemDTO;
import com.app.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDTO> retrieveItem(@PathVariable("id") Long id) {
        try{
            //ItemDTO itemDTO = itemService.getItemById(Optional.ofNullable(id).orElseThrow(IllegalStateException::new));
            ItemDTO itemDTO = itemService.getItemById(id);
            return ResponseEntity.ok(itemDTO);
        //} catch (IllegalStateException|ItemException ie) {
        } catch (ItemException ie) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (ItemNotFoundException nf) {
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
            itemDTO.validate();   //Must validate, DTO auto loaded from Json
            itemDTO = itemService.saveItem(itemDTO);
            return ResponseEntity.ok(itemDTO);
        } catch (ItemValidateException iv) {
            return ResponseEntity.badRequest().body(iv.getMessage());
        } catch (ItemException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
        try {
            //ItemDTO itemDTO = itemService.deleteItem(Optional.ofNullable(id).orElseThrow(IllegalStateException::new));
            ItemDTO itemDTO = itemService.deleteItem(id);
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
            itemDTO.validate();   //Must validate, DTO auto loaded from Json
            itemDTO = itemService.updateItem(id, itemDTO);
            return ResponseEntity.ok(itemDTO);
        } catch (ItemValidateException iv) {
            return ResponseEntity.badRequest().body(iv.getMessage());
        } catch (ItemNotFoundException nf) {
            return ResponseEntity.notFound().build();
        } catch (ItemException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
    @GetMapping("/items/{X_type}")
    public ResponseEntity<List<ItemDTO>> retrieveItemsByType(@PathVariable("X_type") String type) {
        try{
            List<ItemDTO> itemsDTO = itemService.getItemsByType(type);
            return ResponseEntity.ok(itemsDTO);
        } catch (ItemException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
