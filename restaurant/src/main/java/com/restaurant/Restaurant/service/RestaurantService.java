package com.restaurant.Restaurant.service;


import com.restaurant.Restaurant.model.Restaurant;
import com.restaurant.Restaurant.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        return restaurantRepository.findById(id);
    }

    public Optional<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }


    public Optional<Restaurant> updateRestaurant(String id, Restaurant updatedRestaurant) {
        return restaurantRepository.findById(id).map(existing -> {
            existing.setName(updatedRestaurant.getName());
            existing.setEmail(updatedRestaurant.getEmail());
            existing.setPassword(updatedRestaurant.getPassword());
            existing.setImage(updatedRestaurant.getImage());
            existing.setPhone(updatedRestaurant.getPhone());
            existing.setAddress(updatedRestaurant.getAddress());
            existing.setOwnerName(updatedRestaurant.getOwnerName());
            existing.setOpeningTime(updatedRestaurant.getOpeningTime());
            existing.setClosingTime(updatedRestaurant.getClosingTime());

            return restaurantRepository.save(existing);
        });
    }

    public boolean deleteRestaurant(String id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }

}