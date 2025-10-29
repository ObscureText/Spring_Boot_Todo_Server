package com.example.todo.constants;

public class AppConstants {
    private AppConstants() {}

    public static final String EMPTY_STRING = "";

    public static class Header {
        private Header() {}

        public static final String SET_COOKIE = "Set-Cookie";
    }

    public static class RequestField {
        private RequestField() {}

        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
    }

    public static class Cookie {
        private Cookie() {}

        public static final String TOKEN = "token";
        public static final String PATH_ROOT = "/";
        public static final String SAME_SITE = "Lax";
        public static final boolean HTTP_ONLY = true;
        public static final boolean SECURE = false;
        public static final int MAX_AGE_ZERO = 0;
        public static final int MAX_AGE_TEN_MINS = 600;
    }

    public static class Cors {
        private Cors() {}

        public static final String ALLOWED_ORIGIN = "http://localhost:3000";
        public static final String ALLOWED_PATHS = "/**";
        public static final String ALLOWED_METHODS = "*";
        public static final String ALLOWED_HEADERS = "*";
    }

    public static class Interceptor {
        private Interceptor() {}

        public static final String TODO_PATHS = "/todo/**";
    }
}
