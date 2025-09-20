package com.demo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.demo.model.User;
import com.demo.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.insert(user);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        // TODO: implement get all users

        throw new IllegalStateException("Não implementado");
    }

    public void deleteUserById(Long userId) {
        // TODO: implement delete user by id

        throw new IllegalStateException("Não implementado");
    }
}
