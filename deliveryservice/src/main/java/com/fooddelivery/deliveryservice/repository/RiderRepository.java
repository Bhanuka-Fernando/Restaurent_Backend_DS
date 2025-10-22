package com.fooddelivery.deliveryservice.repository;

import com.fooddelivery.deliveryservice.model.Rider;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RiderRepository extends MongoRepository<Rider, String> {
    Optional<Rider> findByEmail(String email); // 🔍 Used for login
}
