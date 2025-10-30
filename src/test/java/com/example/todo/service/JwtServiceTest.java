package com.example.todo.service;

import com.example.todo.constants.Messages;
import com.example.todo.exception.AppException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {
    private final JwtService jwtService = new JwtService();
    private static final String SECRET = "this-is-a-secret-key-with-long-characters";
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    @Test
    void generateToken_ValidateUsername() {
        final String token = jwtService.generateToken("favas");
        assertNotNull(token);

        final String username = jwtService.validateAndGetUsername(token);
        assertEquals("favas", username);
    }

    @Test
    void validateAndGetUsernameInvalidToken() {
        final String invalidToken = "this-is-an-invalid-token";

        final AppException exception = assertThrows(
                AppException.class,
                () -> jwtService.validateAndGetUsername(invalidToken)
        );

        assertEquals(HttpStatus.UNAUTHORIZED, exception.status);
        assertEquals(Messages.Error.INVALID_TOKEN, exception.getMessage());
    }

    @Test
    void validateAndGetUsernameExpiredToken() {
        final String expiredToken = Jwts.builder()
                .setSubject("favas")
                .setIssuedAt(new Date(System.currentTimeMillis() - 2000))
                .setExpiration(new Date(System.currentTimeMillis() - 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        final AppException exception = assertThrows(
                AppException.class,
                () -> jwtService.validateAndGetUsername(expiredToken)
        );

        assertEquals(HttpStatus.UNAUTHORIZED, exception.status);
        assertEquals(Messages.Error.TOKEN_EXPIRED, exception.getMessage());
    }
}