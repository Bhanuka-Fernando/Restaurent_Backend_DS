package com.fooddelivery.deliveryservice.service;

import com.fooddelivery.deliveryservice.model.Delivery;
import com.fooddelivery.deliveryservice.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    // Create a new delivery
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    // Get deliveries assigned to a rider
    public List<Delivery> getDeliveriesForRider(String riderId) {
        return deliveryRepository.findByDeliveryPersonnelId(riderId);
    }

    // Update delivery status
    public Delivery updateDeliveryStatus(String deliveryId, String newStatus) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            delivery.setStatus(newStatus);
            return deliveryRepository.save(delivery);
        }
        return null;
    }
}
