package com.example.todo.controller;

import com.example.todo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided");
        }

        if (service.getUserNameFromSession(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        return ResponseEntity.ok("Session verified");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        final String username = body.get("username");
        final String password = body.get("password");

        final String token = service.login(username, password);

        if (token != null) {
            final ResponseCookie cookie = ResponseCookie
                    .from("token", token)
                    .path("/")
                    .maxAge(60 * 10)
                    .sameSite("Lax")
                    .build();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Set-Cookie", cookie.toString())
                    .body("Login successful");
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username of password");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue(value = "token", required = false) String token) {
        service.logout(token);

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
