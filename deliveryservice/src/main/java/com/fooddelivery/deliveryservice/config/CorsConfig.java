// deliveryservice/src/main/java/com/fooddelivery/deliveryservice/config/CorsConfig.java
package com.fooddelivery.deliveryservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",  // rider UI (if any)
                        "http://localhost:3001",  // restaurant UI  ✅ add this
                        "http://localhost:5173",
                        "http://localhost:5174"
                )
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
