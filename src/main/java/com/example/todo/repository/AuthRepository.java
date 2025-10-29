package com.example.todo.repository;

import com.example.todo.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthRepository {
    private final List<User> users = List.of(
        new User("one", "1", "Name One"),
        new User("two", "2", "Name Two"),
        new User("three", "3", "Name Tree")
    );

    public User getUserByUsername(String username) {
        try {
            Thread.sleep(500);
            return users.stream()
                    .filter(user -> user.username.equals(username))
                    .findFirst()
                    .orElse(null);
        } catch(Exception e) {
            return null;
        }
    }
}
