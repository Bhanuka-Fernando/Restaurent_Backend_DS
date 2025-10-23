package com.fooddelivery.deliveryservice.service;

import com.fooddelivery.deliveryservice.model.Delivery;
import com.fooddelivery.deliveryservice.repository.DeliveryRepository;
import com.fooddelivery.deliveryservice.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private RiderRepository riderRepository;

    // Create a new delivery
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    // Get deliveries assigned to a rider
    public List<Delivery> getDeliveriesForRider(String riderEmail) {
        return deliveryRepository.findByRiderEmail(riderEmail);
    }

    // Update delivery status
    public Delivery updateDeliveryStatus(String deliveryId, String newStatus) {
        return deliveryRepository.findById(deliveryId).map(d -> {
            d.setStatus(newStatus);
            Delivery saved = deliveryRepository.save(d);

            if ("DELIVERED".equalsIgnoreCase(newStatus) && d.getRiderEmail() != null) {
                riderRepository.findByEmail(d.getRiderEmail()).ifPresent(r -> {
                    r.setCurrentLoad(Math.max(0, r.getCurrentLoad() - 1));
                    if (r.getCurrentLoad() == 0) r.setStatus("AVAILABLE");
                    riderRepository.save(r);
                });
            }
            return saved;
        }).orElseThrow(() -> new RuntimeException("Delivery not found: " + deliveryId));
    }
}
