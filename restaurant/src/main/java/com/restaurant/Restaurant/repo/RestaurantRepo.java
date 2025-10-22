package com.restaurant.Restaurant.repo;


import com.restaurant.Restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepo extends MongoRepository<Restaurant,String> {
    List<Restaurant> findByOwnerName(String ownerName);
    Optional<Restaurant> findByName(String name);



}
