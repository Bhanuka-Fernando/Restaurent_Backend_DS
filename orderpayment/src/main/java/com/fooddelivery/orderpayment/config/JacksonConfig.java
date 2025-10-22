package com.fooddelivery.orderpayment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        org.springframework.http.converter.json.Jackson2ObjectMapperBuilder Jackson2ObjectMapperBuilder = null;
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
