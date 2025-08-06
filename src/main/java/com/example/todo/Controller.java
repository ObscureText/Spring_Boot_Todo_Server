package com.example.todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/")
    public String root() {
        return "Hello world";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello - " + name;
    }

    @PostMapping("/add")
    public String addTask(@RequestBody String task) {
        return "Adds - " + task;
    }

    @GetMapping("/tasks")
    public List<Map<String, String>> getTasks() {
        return List.of(
            Map.of("id", "111", "title", "Do this and this"),
            Map.of("id", "222", "title", "Wash car"),
            Map.of("id", "333", "title", "Wash bus")
        );
    }
}
