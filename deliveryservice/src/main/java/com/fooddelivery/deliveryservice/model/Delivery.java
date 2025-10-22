package com.fooddelivery.deliveryservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "deliveries")
public class Delivery {

    @Id
    private String id;
    private String orderId;
    private String customerId;
    private String customerAddress; // New field for customer's address
    private String deliveryPersonnelId; // Rider's ID (assigned to delivery)
    private String status; // e.g., "pending", "in-transit", "delivered"
    private String location; // Pickup address or coordinates
    private Date deliveryTime; // Timestamp of delivery
    private double orderAmount; // 💵 Total value of order (new field)

    // Constructor (with customerAddress and orderAmount included)
    public Delivery(String orderId, String customerId, String customerAddress, String deliveryPersonnelId, String status, String location, Date deliveryTime, double orderAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerAddress = customerAddress; // Setting the customer address
        this.deliveryPersonnelId = deliveryPersonnelId;
        this.status = status;
        this.location = location;
        this.deliveryTime = deliveryTime;
        this.orderAmount = orderAmount;
    }

    // Empty constructor (required by Spring Data)
    public Delivery() {}

    // Getters and Setters
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddress() {
        return customerAddress; // Get customer address
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress; // Set customer address
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

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }
}
