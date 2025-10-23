package com.fooddelivery.orderpayment.repository;

import com.fooddelivery.orderpayment.model.Order;
import com.fooddelivery.orderpayment.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;


// talking to MongoDB
public interface OrderRepository extends MongoRepository<Order,String> {

    List<Order> findByCustomerId(String customerId);

    List<Order> findByItemsRestaurantId(String restaurantId);

}
