package com.example.todo.constants;

public class EndPointUrls {
    private EndPointUrls() {}

    public static class Auth {
        private Auth() {}

        public static final String BASE = "/auth";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String VERIFY = "/verify";
    }

    public static class Todo {
        private Todo() {}

        public static final String BASE = "/todo";
        public static final String TASKS = "/tasks";
    }
}
