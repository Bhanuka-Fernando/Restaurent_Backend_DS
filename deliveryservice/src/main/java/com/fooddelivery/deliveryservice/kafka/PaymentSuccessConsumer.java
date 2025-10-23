package com.fooddelivery.deliveryservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.deliveryservice.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentSuccessConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AssignmentService assignmentService;

    @KafkaListener(topics = "payments.success", groupId = "deliveryservice")
    public void onPaymentSuccess(String json) throws Exception {
        PaymentSucceededEvent evt = objectMapper.readValue(json, PaymentSucceededEvent.class);
        log.info("📥 payments.success received for order {}", evt.getOrderId());
        assignmentService.handle(evt);
    }
}
