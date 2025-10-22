package com.fooddelivery.orderpayment.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fooddelivery.orderpayment.model.Order;
import com.fooddelivery.orderpayment.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController  // tell spring to handle spring requests and return data
@CrossOrigin(origins="*")  // helps to solve cors issue. allow requests from any domain
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) throws JsonProcessingException {  //@RequestBody -> takes incoming JSON and maps it to an order object
        return orderService.createOrder(order);
    }


    // @PathVariable -> extracts orderId from the URL
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody Order order){
        return orderService.updateOrder(id, order);
    }

    // get order by customer Id
    @GetMapping("/customer/{customerId}")
    public List<Order> getOrderByCustomer(@PathVariable String customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }




    // get order by orderId
    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    // delete order by id
    @DeleteMapping("/{orderId}/items/{itemId}")
    public void deleteOrderItem(@PathVariable String orderId, @PathVariable String itemId) {
        orderService.deleteOrderItem(orderId, itemId);
    }

    // delete order by id
    @DeleteMapping("/delete/{itemId}")
    public void deleteOrderItemById(@PathVariable String itemId) {
        orderService.deleteOrder(itemId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> getOrdersByRestaurantId(@PathVariable String restaurantId) {
        return orderService.getOrdersByRestaurantId(restaurantId);
    }




}
