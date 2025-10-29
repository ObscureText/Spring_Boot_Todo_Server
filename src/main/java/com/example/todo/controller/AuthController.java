package com.example.todo.controller;

import com.example.todo.models.User;
import com.example.todo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(
    origins = "http://localhost:3000",
    allowCredentials = "true"
)
public class AuthController {
    private final AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@CookieValue(value = "token", required = false) String token) {
        if (token == null || !token.startsWith("user-token-")) {
            return ResponseEntity.status(401).body("No or invalid token");
        }

        final String username = token.substring("user-token-".length());
        final User user = service.getUserByUsername(username);

        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        return ResponseEntity.ok(user.username);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        final String username = body.get("username");
        final String password = body.get("password");

        if (service.login(username, password)) {
            final ResponseCookie cookie = ResponseCookie
                    .from("token", "user-token-" + username)
                    .path("/")
                    .maxAge(60 * 10)
                    .sameSite("Lax")
                    .build();

            return ResponseEntity
                    .status(200)
                    .header("Set-Cookie", cookie.toString())
                    .body("Login successful");
        }

        return ResponseEntity
                .status(401)
                .body("Invalid username of password");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        final ResponseCookie cookie = ResponseCookie
                .from("token", "")
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        return ResponseEntity
                .ok()
                .header("Set-Cookie", cookie.toString())
                .body("Logged out successfully");
    }
}
