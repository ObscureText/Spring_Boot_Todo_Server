package com.example.todo.config;

import com.example.todo.constants.AppConstants;
import com.example.todo.middleware.AppInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    private final AppInterceptor authInterceptor;

    @Autowired
    public AppConfig(AppInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns(AppConstants.Interceptor.TODO_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(AppConstants.Cors.ALLOWED_PATHS)
                .allowedOrigins(AppConstants.Cors.ALLOWED_ORIGIN)
                .allowedMethods(AppConstants.Cors.ALLOWED_METHODS)
                .allowedHeaders(AppConstants.Cors.ALLOWED_HEADERS)
                .allowCredentials(true);
    }
}
