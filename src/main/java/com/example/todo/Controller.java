package com.example.todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/")
    public String root() {
        return "Hello there ..!";
    }

//    @GetMapping("/greet")
//    public String greet(@RequestParam String name) {
//        return "Hello - " + name;
//    }

//    @PostMapping("/add")
//    public String addTask(@RequestBody String task) {
//        return "Adds - " + task;
//    }

//    @PostMapping("/tasks")
//    public List<Map<String, String>> postTasks(@RequestBody String userId) {
//        List<Map<String, String>> tasks =  List.of(
//            Map.of("taskId", "1","userId", "1", "title", "For user one - 1"),
//            Map.of("taskId", "2","userId", "1", "title", "For user one - 2"),
//            Map.of("taskId", "3","userId", "1", "title", "For user one - 3"),
//            Map.of("taskId", "4","userId", "2", "title", "For user two - 1"),
//            Map.of("taskId", "5","userId", "2", "title", "For user two - 2"),
//            Map.of("taskId", "6","userId", "2", "title", "For user two - 3"),
//            Map.of("taskId", "7","userId", "3", "title", "For user three - 1"),
//            Map.of("taskId", "8","userId", "3", "title", "For user three - 2"),
//            Map.of("taskId", "9","userId", "3", "title", "For user three - 3")
//        );
//
//        return tasks.stream().filter(task -> task.get("userId").equals(userId)).toList();
//    }

//    @GetMapping("/tasks")
//    public List<Map<String, String>> getTasks(@RequestParam String userId) {
//        List<Map<String, String>> tasks =  List.of(
//                Map.of("taskId", "1","userId", "1", "title", "For user one - 1"),
//                Map.of("taskId", "2","userId", "1", "title", "For user one - 2"),
//                Map.of("taskId", "3","userId", "1", "title", "For user one - 3"),
//                Map.of("taskId", "4","userId", "2", "title", "For user two - 1"),
//                Map.of("taskId", "5","userId", "2", "title", "For user two - 2"),
//                Map.of("taskId", "6","userId", "2", "title", "For user two - 3"),
//                Map.of("taskId", "7","userId", "3", "title", "For user three - 1"),
//                Map.of("taskId", "8","userId", "3", "title", "For user three - 2"),
//                Map.of("taskId", "9","userId", "3", "title", "For user three - 3")
//        );
//
//        return tasks.stream().filter(task -> task.get("userId").equals(userId)).toList();
//    }
}
