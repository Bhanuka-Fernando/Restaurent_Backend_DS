package com.fooddelivery.orderpayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String customerId;
    private List<OrderItem> items;
    private double totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private String orderImage;
    private String fullName;
    private String email;
    private String deliveryLocation;
    private String restaurantName;
}
