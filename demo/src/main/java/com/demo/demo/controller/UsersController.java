package com.demo.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.exception.NotFoundException;
import com.demo.demo.model.ErrorResponse;
import com.demo.demo.model.User;
import com.demo.demo.service.UserService;

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
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.NOT_FOUND,
                    "usuário com id: " + userId + " não encontrado");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.NOT_FOUND,
                    "usuário com username: " + username + " não encontrado");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            user = userService.updateUser(user);
            return ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.NOT_FOUND,
                    "usuário não encontrado");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> removeUser(@PathVariable Long userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.NOT_FOUND,
                    "usuário não encontrado");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }
}
