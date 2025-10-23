package com.fooddelivery.deliveryservice.service;

import com.fooddelivery.deliveryservice.model.Rider;
import com.fooddelivery.deliveryservice.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// === src/main/java/com/fooddelivery/deliveryservice/service/RiderService.java ===
@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

    // Register rider and return the saved rider object (with ID)
    public Rider registerRider(Rider rider) {
        if (rider.getStatus() == null) rider.setStatus("AVAILABLE");
        rider.setCurrentLoad(0);
        if (rider.getMaxConcurrent() <= 0) rider.setMaxConcurrent(1);
        return riderRepository.save(rider);
    }

    // Authenticate rider during login
    public Rider authenticateRider(String email, String password) {
        Optional<Rider> optionalRider = riderRepository.findByEmail(email);

        if (optionalRider.isPresent()) {
            Rider rider = optionalRider.get();
            // Check if the provided password matches the stored one (plain-text for now, ideally should use hashing)
            if (rider.getPassword().equals(password)) {
                return rider; // Return rider object if passwords match
            } else {
                System.out.println("Password mismatch");
            }
        } else {
            System.out.println("No rider found with email");
        }

        return null; // Return null if no rider or password mismatch
    }

    // New method to get all riders
    public List<Rider> getAllRiders() {
        return riderRepository.findAll();  // Fetch all riders from the repository
    }
}
