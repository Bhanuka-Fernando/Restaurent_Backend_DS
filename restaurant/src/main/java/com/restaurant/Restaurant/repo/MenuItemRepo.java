package com.restaurant.Restaurant.repo;

import com.restaurant.Restaurant.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepo extends MongoRepository<MenuItem, String> {
    List<MenuItem> findByRestaurantId(String restaurantId);
}


