package com.fooddelivery.deliveryservice.repository;

import com.fooddelivery.deliveryservice.model.Rider;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RiderRepository extends MongoRepository<Rider, String> {
    Optional<Rider> findFirstByZoneAndStatusOrderByCurrentLoadAsc(String zone, String status);
    List<Rider> findByStatus(String status);
    Optional<Rider> findByEmail(String email); // 🔍 Used for login
}
