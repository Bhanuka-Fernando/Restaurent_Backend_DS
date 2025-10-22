package com.fooddelivery.orderpayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    private String paymentIdFromStripe;
    private String sessionId;
    private String sessionUrl;
    private String status;
    private String itemName;
    private Long amount;
    private Long quantity;
    private String currency;

    private String customerId;
    private String orderId;
    private String paymentStatus;
    private LocalDateTime paidAt;

}
