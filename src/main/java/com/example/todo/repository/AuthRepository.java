package com.example.todo.repository;

import com.example.todo.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class AuthRepository {
    private final List<User> users = List.of(
        new User("1", "1", "Name One"),
        new User("1", "2", "Name Two"),
        new User("1", "3", "Name Tree")
    );

    private final HashMap<String, User> sessions = new HashMap<>();

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

    public String createUserSession(User user) {
        final String id = UUID.randomUUID().toString();
        sessions.put(id, user);
        return id;
    }

    public void deleteUserSession(String token) {
        sessions.remove(token);
    }

    public String getUserNameFromSession(String token) {
        final User user =  sessions.get(token);
        return user != null ? user.username : null;
    }
}
