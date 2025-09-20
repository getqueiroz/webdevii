package com.demo.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.demo.exception.NotFoundException;
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

    public User getUserById(Long userId) throws NotFoundException {
        return userRepository.findById(userId);
    }

    public User getUserByUsername(String username) throws NotFoundException {
        //TODO implementar
        throw new IllegalStateException("não implementado");
    }

    public List<User> getAllUsers() {
        //TODO: implementar
        throw new IllegalStateException("não implementado");
    }

    public User updateUser(User userUpdated) {
        //TODO: implementar
        
        throw new IllegalStateException("não implementado");
    }

    public void deleteUserById(Long userId) throws NotFoundException {
        //TODO: implementar 
        
        throw new IllegalStateException("não implementado");
    }
}
