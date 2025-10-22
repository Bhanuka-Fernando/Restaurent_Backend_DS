package com.fooddelivery.orderpayment.services;

import com.fooddelivery.orderpayment.model.Payment;
import com.fooddelivery.orderpayment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(String paymentIdFromStripe, String customerId, String orderId, Long amount, String status) {
        Payment payment = new Payment();
        payment.setPaymentIdFromStripe(paymentIdFromStripe);
        payment.setCustomerId(customerId);
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setPaymentStatus(status);
        payment.setPaidAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public Payment savePaymentDirect(Payment payment) {
        payment.setPaidAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    // For getting all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // get payment by ID
    public Optional<Payment> getPaymentById(String id) {
        return paymentRepository.findById(id);
    }

    public Payment updatePayment(String id, Payment updatedPayment) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setPaymentIdFromStripe(updatedPayment.getPaymentIdFromStripe());
            payment.setCustomerId(updatedPayment.getCustomerId());
            payment.setOrderId(updatedPayment.getOrderId());
            payment.setAmount(updatedPayment.getAmount());
            payment.setPaymentStatus(updatedPayment.getPaymentStatus());
            payment.setPaidAt(LocalDateTime.now());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
    }

    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

}
