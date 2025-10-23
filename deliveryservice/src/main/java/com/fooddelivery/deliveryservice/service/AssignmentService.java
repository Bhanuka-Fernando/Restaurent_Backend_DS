package com.fooddelivery.deliveryservice.service;

import com.fooddelivery.deliveryservice.kafka.PaymentSucceededEvent;
import com.fooddelivery.deliveryservice.model.Delivery;
import com.fooddelivery.deliveryservice.model.Rider;
import com.fooddelivery.deliveryservice.repository.DeliveryRepository;
import com.fooddelivery.deliveryservice.repository.RiderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentService {
    private final RiderRepository riderRepository;
    private final DeliveryRepository deliveryRepository;
    private final SimpMessagingTemplate ws;

    @Transactional
    public void handle(PaymentSucceededEvent e) {
        // 1) find available rider in same zone, else any available
        Rider rider = riderRepository
                .findFirstByZoneAndStatusOrderByCurrentLoadAsc(e.getRestaurantAddress(), "AVAILABLE")
                .orElseGet(() -> riderRepository.findByStatus("AVAILABLE").stream().findFirst().orElse(null));

        Delivery d = new Delivery(
                null,
                e.getOrderId(),
                e.getRestaurantId(),
                e.getRestaurantName(),
                e.getRestaurantAddress(),
                e.getCustomerId(),
                e.getCustomerAddress(),
                rider != null ? rider.getId() : null,                // deliveryPersonnelId
                rider != null ? "ASSIGNED" : "PENDING",
                e.getRestaurantAddress(),                             // location (pickup)
                Instant.now(),
                e.getTotalPrice() != null ? e.getTotalPrice() : 0.0,  // orderAmount
                rider != null ? rider.getEmail() : null,              // riderEmail
                Instant.now()
        );

        d = deliveryRepository.save(d);

        if (rider != null) {
            rider.setStatus("BUSY");
            rider.setCurrentLoad(rider.getCurrentLoad() + 1);
            riderRepository.save(rider);

            // Push to riders topic (your React already subscribes)
            ws.convertAndSend("/topic/delivery", Map.of(
                    "orderId", d.getOrderId(),
                    "deliveryId", d.getId(),
                    "customerAddress", d.getCustomerAddress(),
                    "location", d.getRestaurantAddress(),
                    "status", d.getStatus(),
                    "deliveryTime", Instant.now().toString(),
                    "orderAmount", d.getOrderAmount()
            ));
            log.info("✅ Assigned order {} to rider {}", e.getOrderId(), rider.getEmail());
        } else {
            log.warn("⏳ No available rider. Delivery {} is PENDING", e.getOrderId());
        }
    }
}
