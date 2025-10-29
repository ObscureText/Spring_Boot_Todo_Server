package com.example.todo.service;

import com.example.todo.models.User;
import com.example.todo.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository repository;

    @Autowired
    public AuthService(AuthRepository repository) {
        this.repository = repository;
    }

    public Boolean login(String username, String password) {
        final User user = repository.getUserByUsername(username);
        return user != null && user.password.equals(password);
    }

    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }
}
