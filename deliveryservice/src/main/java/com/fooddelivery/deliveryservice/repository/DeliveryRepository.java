package com.fooddelivery.deliveryservice.repository;

import com.fooddelivery.deliveryservice.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {

    // Find deliveries by rider (deliveryPersonnelId) - for the rider's dashboard
    List<Delivery> findByDeliveryPersonnelId(String deliveryPersonnelId);

    // Find deliveries by customer ID
    List<Delivery> findByCustomerId(String customerId);

    // Find deliveries by delivery status (e.g., "pending", "in-transit", "delivered")
    List<Delivery> findByStatus(String status);
}
