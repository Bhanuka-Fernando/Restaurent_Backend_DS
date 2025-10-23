package com.fooddelivery.deliveryservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "deliveries")
public class Delivery {

    @Id
    private String id;
    private String orderId;
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String customerId;
    private String customerAddress; // New field for customer's address
    private String deliveryPersonnelId; // Rider's ID (assigned to delivery)
    private String status; // e.g., "pending", "in-transit", "delivered"
    private String location; // Pickup address or coordinates
    private Instant deliveryTime; // Timestamp of delivery
    private double orderAmount; // 💵 Total value of order (new field)
    private String riderEmail;
    private Instant createdAt;

    public Delivery(String id, String orderId, String restaurantId, String restaurantName, String restaurantAddress, String customerId, String customerAddress, String deliveryPersonnelId, String status, String location, Instant deliveryTime, double orderAmount, String riderEmail, Instant createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.deliveryPersonnelId = deliveryPersonnelId;
        this.status = status;
        this.location = location;
        this.deliveryTime = deliveryTime;
        this.orderAmount = orderAmount;
        this.riderEmail = riderEmail;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getDeliveryPersonnelId() {
        return deliveryPersonnelId;
    }

    public void setDeliveryPersonnelId(String deliveryPersonnelId) {
        this.deliveryPersonnelId = deliveryPersonnelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Instant getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Instant deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getRiderEmail() {
        return riderEmail;
    }

    public void setRiderEmail(String riderEmail) {
        this.riderEmail = riderEmail;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
