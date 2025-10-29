package com.example.todo.controller;

import com.example.todo.constants.AppConstants;
import com.example.todo.constants.EndPointUrls;
import com.example.todo.models.Task;
import com.example.todo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(EndPointUrls.Todo.BASE)
@RestController
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping(EndPointUrls.Todo.TASKS)
    public List<Task> tasks(HttpServletRequest request) {
        final String username = (String) request.getAttribute(AppConstants.RequestField.USERNAME);
        return Optional.ofNullable(service.tasks(username)).orElse(List.of());
    }
}
