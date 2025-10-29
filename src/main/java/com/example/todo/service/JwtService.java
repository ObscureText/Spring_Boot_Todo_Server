package com.example.todo.service;

import com.example.todo.constants.Messages;
import com.example.todo.exception.AppException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "this-is-a-secret-key-with-long-characters";
    private static final int EXPIRY_MS = 1000 * 60 * 10;
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateAndGetUsername(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return claims.getBody().getSubject();
        } catch (ExpiredJwtException e) {
            throw new AppException(HttpStatus.UNAUTHORIZED, Messages.Error.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new AppException(HttpStatus.UNAUTHORIZED, Messages.Error.INVALID_TOKEN);
        }
    }
}
