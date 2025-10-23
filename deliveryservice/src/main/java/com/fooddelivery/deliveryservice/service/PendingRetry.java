package com.fooddelivery.deliveryservice.service;

import com.fooddelivery.deliveryservice.kafka.PaymentSucceededEvent;
import com.fooddelivery.deliveryservice.model.Delivery;
import com.fooddelivery.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendingRetry {

    private final DeliveryRepository deliveryRepository;
    private final AssignmentService assignment;

    @Scheduled(fixedDelay = 30000)
    public void retry() {
        for (Delivery d : deliveryRepository.findByStatus("PENDING")) {
            assignment.handle(toEvt(d));
        }
    }

    private PaymentSucceededEvent toEvt(Delivery d) {
        PaymentSucceededEvent e = new PaymentSucceededEvent();
        e.setOrderId(d.getOrderId());
        e.setRestaurantId(d.getRestaurantId());
        e.setRestaurantName(d.getRestaurantName());
        e.setRestaurantAddress(d.getRestaurantAddress());
        e.setCustomerId(d.getCustomerId());
        e.setCustomerAddress(d.getCustomerAddress());
        e.setTotalPrice(d.getOrderAmount());
        return e;
    }
}
