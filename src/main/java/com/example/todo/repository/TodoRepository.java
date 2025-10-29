package com.example.todo.repository;

import com.example.todo.models.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TodoRepository {
    private final Map<String, List<Task>> tasks = Map.of(
        "1", List.of(
            new Task("1", "Car wash", false),
            new Task("2", "Buy groceries", true),
            new Task("3", "Finish Spring Boot tutorial", false)
        ),
        "2", List.of(),
        "3", List.of(
            new Task("6", "Read a book", false),
            new Task("7", "Call mom", true),
            new Task("8", "Clean room", false)
        )
    );

    public List<Task> tasks(String userId) {
        try {
            Thread.sleep(500);
            return tasks.get(userId);
        } catch (Exception e) {
            return null;
        }
    }
}
