package com.example.todo.controller;

import com.example.todo.constants.AppConstants;
import com.example.todo.constants.EndPointUrls;
import com.example.todo.constants.Messages;
import com.example.todo.exception.AppException;
import com.example.todo.service.AuthService;
import com.example.todo.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(EndPointUrls.Auth.BASE)
public class AuthController {
    private final AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @GetMapping(EndPointUrls.Auth.VERIFY)
    public ResponseEntity<String> verify(@CookieValue(value = AppConstants.Cookie.TOKEN, required = false) String token) {
        if (token == null) {
            throw new AppException(HttpStatus.UNAUTHORIZED, Messages.Error.NO_TOKEN_PROVIDED);
        }

        final String username = service.getUserNameFromSession(token);
        return ResponseEntity.ok(username);
    }

    @PostMapping(EndPointUrls.Auth.LOGIN)
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        final String username = body.get(AppConstants.RequestField.USERNAME);
        final String password = body.get(AppConstants.RequestField.PASSWORD);

        final String token = service.login(username, password);
        final ResponseCookie cookie = ResponseCookie
                .from(AppConstants.Cookie.TOKEN, token)
                .path(AppConstants.Cookie.PATH_ROOT)
                .maxAge(AppConstants.Cookie.MAX_AGE_TEN_MINS)
                .sameSite(AppConstants.Cookie.SAME_SITE)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(AppConstants.Header.SET_COOKIE, cookie.toString())
                .body(Messages.Success.LOGIN_SUCCESSFUL);
    }

    @PostMapping(EndPointUrls.Auth.LOGOUT)
    public ResponseEntity<String> logout(@CookieValue(value = AppConstants.Cookie.TOKEN, required = false) String token) {
        service.logout(token);

        final ResponseCookie cookie = CookieUtils.deleteAuthCookie();

        return ResponseEntity
                .ok()
                .header(AppConstants.Header.SET_COOKIE, cookie.toString())
                .body(Messages.Success.LOGOUT_SUCCESSFUL);
    }
}
