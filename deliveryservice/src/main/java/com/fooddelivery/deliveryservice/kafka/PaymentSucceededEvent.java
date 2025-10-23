package com.fooddelivery.deliveryservice.kafka;

import lombok.Data;

@Data
public class PaymentSucceededEvent {
    private String orderId;
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress; // used as zone
    private String customerId;
    private String customerAddress;
    private Double totalPrice;
    private String paidAtIso;
}
