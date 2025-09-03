package com.demo.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.model.User;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UsersController {

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return user;
    }
    
    @GetMapping("/{userId}")
    public String getMethodName(@PathVariable Long userId) {
        return new String("userId:" + userId);
    }

}
