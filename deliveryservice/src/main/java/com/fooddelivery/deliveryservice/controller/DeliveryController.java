package com.fooddelivery.deliveryservice.controller;

import com.fooddelivery.deliveryservice.model.Delivery;
import com.fooddelivery.deliveryservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    // Get all deliveries assigned to a rider
    @GetMapping("/rider/{riderId}")
    public ResponseEntity<List<Delivery>> getAssignedDeliveries(@PathVariable String riderId) {
        List<Delivery> deliveries = deliveryService.getDeliveriesForRider(riderId);
        return ResponseEntity.ok(deliveries);
    }

    // Create a new delivery
    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        // Make sure to include customer address in the request body
        return ResponseEntity.ok(deliveryService.createDelivery(delivery));
    }

    // Update the delivery status
    @PutMapping("/{id}/status")
    public ResponseEntity<Delivery> updateDeliveryStatus(@PathVariable String id, @RequestParam String status) {
        Delivery updatedDelivery = deliveryService.updateDeliveryStatus(id, status);
        return ResponseEntity.ok(updatedDelivery);
    }
}
