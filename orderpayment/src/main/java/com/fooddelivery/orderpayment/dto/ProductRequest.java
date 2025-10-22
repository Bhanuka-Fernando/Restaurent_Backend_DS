package com.fooddelivery.orderpayment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // adds setters, getters, toString, equals, hashcode
@NoArgsConstructor  // create no argument constructor
@AllArgsConstructor  // create a constructor with all fields
public class ProductRequest {
    private Long amount;
    private Long quantity;
    private String name;
    private String currency;
    private String customerId;
}