package com.fooddelivery.orderpayment.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private String itemId;
    private String restaurantId;
    private String name;
    private int quantity;
    private double price;
    private String orderImage;
}
