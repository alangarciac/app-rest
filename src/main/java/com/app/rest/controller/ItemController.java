package com.app.rest.controller;

import com.app.rest.exception.itemExceptions.ItemGeneralException;
import com.app.rest.exception.itemExceptions.ItemNotFoundException;
import com.app.rest.exception.ValidationException;
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
        } catch (ItemGeneralException ie) {
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
        } catch (IllegalStateException | ItemGeneralException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping("/items/new")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        try {
            itemDTO.validate();   //Must validate, DTO auto loaded from Json
            itemDTO = itemService.saveItem(itemDTO);
            return ResponseEntity.ok(itemDTO);
        } catch (ValidationException iv) {
            return ResponseEntity.badRequest().body(iv.getMessage());
        } catch (ItemGeneralException ie) {
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
        } catch (ItemGeneralException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable("id") Long id, @RequestBody ItemDTO itemDTO) {
        try {
            itemDTO.validate();   //Must validate, DTO auto loaded from Json
            itemDTO = itemService.updateItem(id, itemDTO);
            return ResponseEntity.ok(itemDTO);
        } catch (ValidationException iv) {
            return ResponseEntity.badRequest().body(iv.getMessage());
        } catch (ItemNotFoundException nf) {
            return ResponseEntity.notFound().build();
        } catch (ItemGeneralException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
    @GetMapping("/items/{type}")
    public ResponseEntity<List<ItemDTO>> retrieveItemsByType(@PathVariable("type") String type) {
        try{
            List<ItemDTO> itemsDTO = itemService.getItemsByType(type);
            return ResponseEntity.ok(itemsDTO);
        } catch (ItemGeneralException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
