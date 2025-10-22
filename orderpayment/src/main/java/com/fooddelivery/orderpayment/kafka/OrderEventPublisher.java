package com.fooddelivery.orderpayment.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "order-events";

    public void sendOrderEvent(String orderJson) {
        kafkaTemplate.send(TOPIC, orderJson);
        System.out.println("📤 Sent order event to Kafka: " + orderJson);
    }
}
