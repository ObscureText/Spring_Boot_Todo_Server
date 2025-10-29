package com.example.todo.models;

public class Task {
    public String id;
    public String title;
    public Boolean isDone;

    public Task(String id, String title, Boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }
}
