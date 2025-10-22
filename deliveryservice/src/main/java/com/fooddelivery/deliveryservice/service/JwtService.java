package com.fooddelivery.deliveryservice.service;

import com.fooddelivery.deliveryservice.model.Rider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final String SECRET_KEY = "u8h9v3b2n1z8x6k4p0q7t5r3e1w2y4m6"; // Replace with your secure key

    // Method to generate JWT token
    public String generateToken(Rider rider) {
        return Jwts.builder()
                .setSubject(rider.getEmail())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
