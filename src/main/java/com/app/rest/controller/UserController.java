package com.app.rest.controller;

import com.app.rest.exception.UserNotFoundException;
import com.app.rest.exception.UserPersistenceException;
import com.app.rest.model.dto.UserDTO;
import com.app.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> retrieveUsers() {
        try{
            List<UserDTO> usersDTO = userService.getUsers();
            return ResponseEntity.ok(usersDTO);
        } catch (IllegalStateException | UserPersistenceException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> retrieveUser(@PathVariable("id") Long id) {
        try{
            UserDTO userDTO = userService.findUserById(Optional.ofNullable(id).orElseThrow(IllegalStateException::new));
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException nf) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users/new")
    public ResponseEntity<Long> createUser(@RequestBody UserDTO user) {
        try{
            return user.isSupported() ?
                    new ResponseEntity<Long>(userService.createNewUser(user), HttpStatus.CREATED) : ResponseEntity.badRequest().build();
        } catch (UserPersistenceException ie) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            UserDTO userDTO = userService.deleteUser(id);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException | UserPersistenceException nf) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        try {
            userDTO.validate();   //Must validate, DTO auto loaded from Json
            userDTO = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (UserPersistenceException e) {
            e.printStackTrace();
        }
    }

}
