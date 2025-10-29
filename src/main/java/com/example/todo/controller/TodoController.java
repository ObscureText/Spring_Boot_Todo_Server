package com.example.todo.controller;

import com.example.todo.repository.TodoRepository;
import com.example.todo.models.Task;
import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(
    origins = "http://localhost:3000",
    allowCredentials = "true"
)
@RequestMapping("/todo")
@RestController
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping("/tasks")
    public List<Task> tasks(@RequestBody String username) {
        return Optional.ofNullable(service.tasks(username)).orElse(List.of());
    }
}
