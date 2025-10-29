package com.example.todo.middleware;

import com.example.todo.constants.AppConstants;
import com.example.todo.constants.Messages;
import com.example.todo.exception.AppException;
import com.example.todo.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AppInterceptor implements HandlerInterceptor {
    private final AuthService service;

    public AppInterceptor(AuthService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull Object handler
    ) {
        final Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AppConstants.Cookie.TOKEN) && cookie.getValue() != null) {
                    final String username = service.getUserNameFromSession(cookie.getValue());
                    request.setAttribute(AppConstants.RequestField.USERNAME, username);

                    return true;
                }
            }
        }

        throw new AppException(HttpStatus.UNAUTHORIZED, Messages.Error.NO_TOKEN_PROVIDED);
    }
}
