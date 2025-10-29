package com.example.todo.constants;

public class Messages {
    private Messages() {}

    public static class Error {
        private Error() {}

        public static final String INVALID_TOKEN = "Invalid token";
        public static final String USER_NOT_FOUND = "User not found";
        public static final String NO_TOKEN_PROVIDED = "No token provided";
        public static final String SOMETHING_WENT_WRONG = "Something went wrong";
        public static final String INCORRECT_CREDENTIALS = "Incorrect username or password";
    }

    public static class Success {
        private Success() {}

        public static final String LOGIN_SUCCESSFUL = "Login successful";
        public static final String LOGOUT_SUCCESSFUL = "Logout successful";
    }
}
