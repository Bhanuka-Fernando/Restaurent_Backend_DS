package com.restaurant.Restaurant.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {

    @KafkaListener(topics = "order-events", groupId = "restaurant-service")
    public void listen(String message) {
        System.out.println("Received order event: " + message);
        System.out.println("Inform Delivery Rider");

    }
}

