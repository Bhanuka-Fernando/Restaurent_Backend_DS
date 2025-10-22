package com.fooddelivery.deliveryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow all paths and allow requests from your frontend URL (localhost:3000)
        registry.addMapping("/**") // Allow all endpoints
                .allowedOrigins("http://localhost:3000",
                        "http://localhost:5174" ) // Frontend URL (React running on port 3000)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow common HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow cookies and authentication
    }
}
