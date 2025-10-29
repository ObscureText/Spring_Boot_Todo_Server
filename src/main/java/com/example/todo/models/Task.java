package com.example.todo.models;

public class Task {
    public String id;
    public String title;
    public boolean isDone;

    public Task(String id, String title, boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }
}
