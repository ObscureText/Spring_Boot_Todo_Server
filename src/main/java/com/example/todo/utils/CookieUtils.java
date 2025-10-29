package com.example.todo.utils;

import com.example.todo.constants.AppConstants;
import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpHeaders;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {

    private CookieUtils() {}

    public static ResponseCookie deleteAuthCookie() {
        return ResponseCookie
                .from(AppConstants.Cookie.TOKEN, AppConstants.EMPTY_STRING)
                .path(AppConstants.Cookie.PATH_ROOT)
                .httpOnly(AppConstants.Cookie.HTTP_ONLY)
                .secure(AppConstants.Cookie.SECURE)
                .sameSite(AppConstants.Cookie.SAME_SITE)
                .maxAge(AppConstants.Cookie.MAX_AGE_ZERO)
                .build();
    }

    public static void attachDeleteCookieHeader(HttpServletResponse response) {
        ResponseCookie cookie = deleteAuthCookie();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
