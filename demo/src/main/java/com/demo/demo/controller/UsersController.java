package com.demo.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.model.User;
import com.demo.demo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            user = userService.createUser(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId).get();
            if(user == null) {
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("usuário com id: " + userId + "não encontrado");
            }
            return ResponseEntity.ok(user);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        //TODO: process GET all users request

        throw new IllegalStateException("Não implementado");
    }

    @PutMapping("{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {
        //TODO: process PUT request
        
        throw new IllegalStateException("Não implementado");
    }

    @DeleteMapping
    public ResponseEntity<?> removeUser(@PathVariable Long userId) {
        //TODO: process delete request

        throw new IllegalStateException("Não implementado");
    }
}
