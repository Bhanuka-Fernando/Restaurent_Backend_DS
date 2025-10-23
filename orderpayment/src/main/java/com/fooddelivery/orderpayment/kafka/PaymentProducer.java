package com.fooddelivery.orderpayment.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.orderpayment.dto.PaymentSucceededEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void publishPaymentSuccess(PaymentSucceededEvent evt){
        try{
            String json = objectMapper.writeValueAsString(evt);
            kafkaTemplate.send("payment.success", evt.getOrderId(), json);
            System.out.println("Payment success"+ json);
        }catch (Exception e){
            throw new RuntimeException("Failed to serialize PaymentSucceededEvent", e);
        }
    }

}
