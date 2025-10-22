package com.fooddelivery.orderpayment.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.orderpayment.kafka.OrderEventPublisher;
import com.fooddelivery.orderpayment.model.Order;
import com.fooddelivery.orderpayment.model.OrderItem;
import com.fooddelivery.orderpayment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;  // provide DB operations like save, findById()
    @Autowired
    private OrderEventPublisher orderEventPublisher;
    @Autowired
    private ObjectMapper objectMapper;

    // create order
    public Order createOrder(Order order) throws JsonProcessingException {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING");

        String json = objectMapper.writeValueAsString(order);
        orderEventPublisher.sendOrderEvent(json);


        return orderRepository.save(order);
    }

    // update order
    public Order updateOrder(String orderId, Order updatedOrder){
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if(existingOrder.isPresent()){
            Order order = existingOrder.get();
            order.setItems(updatedOrder.getItems());
            order.setTotalPrice(updatedOrder.getTotalPrice());
            return orderRepository.save(order);
        }else{
            return null;
        }
    }

    // get order by customer
    public List<Order> getOrdersByCustomer(String customerId){
        return orderRepository.findByCustomerId(customerId);
    }

    // get order by Id
    public Optional<Order> getOrderById(String orderId){
        return orderRepository.findById(orderId);
    }

    // Delete order
    public void deleteOrder(String orderId){
        orderRepository.deleteById(orderId);
    }

    public void deleteOrderItem(String orderId, String itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItem> updatedItems = order.getItems().stream()
                .filter(item -> !item.getItemId().equals(itemId))
                .toList();

        if (updatedItems.isEmpty()) {
            orderRepository.deleteById(orderId);
        } else {
            order.setItems(updatedItems);
            orderRepository.save(order);
        }
    }

    public List<Order> getOrdersByRestaurantId(String restaurantId) {
        return orderRepository.findByItemsRestaurantId(restaurantId);
    }




}