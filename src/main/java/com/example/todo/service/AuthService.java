package com.example.todo.service;

import com.example.todo.constants.Messages;
import com.example.todo.exception.AppException;
import com.example.todo.models.User;
import com.example.todo.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtService service;
    private final AuthRepository repository;

    @Autowired
    public AuthService(AuthRepository repository, JwtService service) {
        this.service = service;
        this.repository = repository;
    }

    public String login(String username, String password) {
        final User user = repository.getUserByUsername(username);

        if (user.password.equals(password)) {
            return service.generateToken(user.username);
        }

        throw new AppException(HttpStatus.BAD_REQUEST, Messages.Error.INCORRECT_CREDENTIALS);
    }

    public String getUserNameFromSession(String token) {
        return repository.getUserNameFromSession(token);
    }
}
