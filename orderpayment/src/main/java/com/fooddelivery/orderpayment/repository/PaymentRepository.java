package com.fooddelivery.orderpayment.repository;

import com.fooddelivery.orderpayment.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment,String> {
}
