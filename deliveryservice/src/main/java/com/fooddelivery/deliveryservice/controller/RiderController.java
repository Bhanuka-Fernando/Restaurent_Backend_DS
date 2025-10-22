package com.fooddelivery.deliveryservice.controller;

import com.fooddelivery.deliveryservice.model.Rider;
import com.fooddelivery.deliveryservice.service.RiderService;
import com.fooddelivery.deliveryservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fooddelivery.deliveryservice.model.AuthResponse;

import java.util.List;

// === src/main/java/com/fooddelivery/deliveryservice/controller/RiderController.java ===
@RestController
@RequestMapping("/riders")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @Autowired
    private JwtService jwtService;

    // Rider registration endpoint
    @PostMapping("/register")
    public ResponseEntity<Rider> registerRider(@RequestBody Rider rider) {
        try {
            // Save rider and get the saved object (with generated ID)
            Rider savedRider = riderService.registerRider(rider);
            return ResponseEntity.ok(savedRider); // Return saved rider (with ID) in the response
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Return error if any
        }
    }

    // Rider login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginRider(@RequestBody Rider rider) {
        Rider authenticatedRider = riderService.authenticateRider(rider.getEmail(), rider.getPassword());

        if (authenticatedRider != null) {
            // Generate JWT token for the rider
            String token = jwtService.generateToken(authenticatedRider);
            return ResponseEntity.ok(new AuthResponse(token)); // Return the token in the response
        } else {
            return ResponseEntity.status(401).body("Invalid email or password"); // Unauthorized if credentials are wrong
        }
    }

    // New endpoint to get all riders
    @GetMapping("/all")
    public ResponseEntity<?> getAllRiders() {
        try {
            List<Rider> riders = riderService.getAllRiders(); // Get all riders from the service
            return ResponseEntity.ok(riders);  // Return the list of riders in the response
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching riders");
        }
    }
}
