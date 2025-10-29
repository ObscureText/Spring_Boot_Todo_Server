package com.example.todo.repository;

import com.example.todo.constants.Messages;
import com.example.todo.exception.AppException;
import com.example.todo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class AuthRepository {
    private final List<User> users = List.of(
            new User("1", "1", "Name One"),
            new User("2", "2", "Name Two"),
            new User("3", "3", "Name Tree")
    );

    private final HashMap<String, User> sessions = new HashMap<>();

    public User getUserByUsername(String username) {
        try {
            Thread.sleep(500);

            return users.stream()
                    .filter(u -> u.username.equals(username))
                    .findFirst()
                    .orElseThrow(() -> new AppException(HttpStatus.UNAUTHORIZED, Messages.Error.USER_NOT_FOUND));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(Messages.Error.SOMETHING_WENT_WRONG);
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
        final User user = sessions.get(token);

        if (user == null) {
            throw new AppException(HttpStatus.UNAUTHORIZED, Messages.Error.INVALID_TOKEN);
        }

        return user.username;
    }
}
