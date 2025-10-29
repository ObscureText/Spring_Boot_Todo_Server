package com.example.todo.utils;

import com.example.todo.constants.AppConstants;
import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpHeaders;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {
    private CookieUtils() {}

    private static ResponseCookie.ResponseCookieBuilder baseCookieBuilder(String value) {
        return ResponseCookie
                .from(AppConstants.Cookie.TOKEN, value)
                .path(AppConstants.Cookie.PATH_ROOT)
                .httpOnly(AppConstants.Cookie.HTTP_ONLY)
                .secure(AppConstants.Cookie.SECURE)
                .sameSite(AppConstants.Cookie.SAME_SITE);
    }

    public static ResponseCookie createAuthCookie(String token) {
        return baseCookieBuilder(token)
                .maxAge(AppConstants.Cookie.MAX_AGE_TEN_MINS)
                .build();
    }

    public static ResponseCookie deleteAuthCookie() {
        return baseCookieBuilder(AppConstants.EMPTY_STRING)
                .maxAge(AppConstants.Cookie.MAX_AGE_ZERO)
                .build();
    }

    public static void attachDeleteCookieHeader(HttpServletResponse response) {
        response.addHeader(HttpHeaders.SET_COOKIE, deleteAuthCookie().toString());
    }
}
