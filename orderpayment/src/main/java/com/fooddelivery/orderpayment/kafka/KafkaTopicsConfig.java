package com.fooddelivery.orderpayment.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicsConfig {
    @Bean
    public NewTopic paymentSuccessTopic(){
        return new NewTopic("payemnt.success", 3, (short) 1);
    }
}
