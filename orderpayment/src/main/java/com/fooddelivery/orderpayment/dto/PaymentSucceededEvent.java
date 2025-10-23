package com.fooddelivery.orderpayment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentSucceededEvent {
    private String orderId;
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String customerId;
    private String customerAddress;
    private Double totalPrice;
    private String paidAtIso;

}
