package com.restaurant.Restaurant.controller;


import com.restaurant.Restaurant.model.Restaurant;
import com.restaurant.Restaurant.service.RestaurantService;
import com.restaurant.Restaurant.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "*") // For React
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/register")
    public ResponseEntity<?> registerRestaurant(@RequestBody Restaurant restaurant) {
        try {
            // Validate time formats
            if (!isValidTimeFormat(restaurant.getOpeningTime()) ||
                    !isValidTimeFormat(restaurant.getClosingTime())) {
                return ResponseEntity.badRequest().body("Invalid time format. Use HH:MM");
            }

            // Set default status pending
          //  restaurant.setStatus("pending");

            Restaurant savedRestaurant = restaurantService.createRestaurant(restaurant);
            return ResponseEntity.ok(savedRestaurant);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error registering restaurant");
        }
    }

    private boolean isValidTimeFormat(String time) {
        return time != null && time.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginRestaurant(@RequestBody Map<String, String> loginData) {
        String name = loginData.get("name");
        String password = loginData.get("password");

        Optional<Restaurant> restaurantOpt = restaurantService.getRestaurantByName(name);

        if (restaurantOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Restaurant not found");
        }

        Restaurant restaurant = restaurantOpt.get();

        if (!restaurant.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        // Generate JWT Token
        String token = JwtUtil.generateToken(restaurant.getId(), "restaurant");

       // if ("pending".equals(restaurant.getStatus())) {
          //  return ResponseEntity.ok(Map.of("status", "pending"));
       // }

        return ResponseEntity.ok(Map.of(
                "token", token,
                "id", restaurant.getId(),
                "name", restaurant.getName()
        ));
    }



    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable String id) {
        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String id, @RequestBody Restaurant updatedRestaurant) {
        return restaurantService.updateRestaurant(id, updatedRestaurant)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        if (restaurantService.deleteRestaurant(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}