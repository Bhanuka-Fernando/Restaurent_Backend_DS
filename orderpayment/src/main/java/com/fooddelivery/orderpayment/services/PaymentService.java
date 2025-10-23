package com.fooddelivery.orderpayment.services;

import com.fooddelivery.orderpayment.dto.PaymentSucceededEvent;
import com.fooddelivery.orderpayment.kafka.PaymentProducer;
import com.fooddelivery.orderpayment.model.Payment;
import com.fooddelivery.orderpayment.repository.OrderRepository;
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
    @Autowired
    private PaymentProducer paymentProducer;
    @Autowired
    private OrderRepository orderRepository;

    private void maybePublishPaymentSuccess(Payment payment){
        if(payment == null) return;
        String status = payment.getPaymentStatus() != null ? payment.getPaymentStatus() : payment.getStatus();
        if(status == null) return;

        if("SUCCESS".equalsIgnoreCase(status)){
            var orderOpt = orderRepository.findById(payment.getOrderId());
            orderOpt.ifPresent(order -> {
                var firstItem = order.getItems() != null && !order.getItems().isEmpty() ? order.getItems().get(0) : null;
                var evt = PaymentSucceededEvent.builder()
                        .orderId(payment.getOrderId())
                        .customerId(order.getCustomerId())
                        .customerAddress(order.getDeliveryLocation()) // rename if different
                        .totalPrice(order.getTotalPrice() != null ? order.getTotalPrice().doubleValue() : null)
                        .restaurantId(firstItem != null ? firstItem.getRestaurantId() : null)
                        .restaurantName(firstItem != null ? firstItem.getRestaurantName() : null)
                        .paidAtIso(payment.getPaidAt() != null ? payment.getPaidAt().toString() : null)
                        .build();

                paymentProducer.publishPaymentSuccess(evt);
            });
        }
    }

    public Payment savePayment(String paymentIdFromStripe, String customerId, String orderId, Long amount, String status) {
        Payment payment = new Payment();
        payment.setPaymentIdFromStripe(paymentIdFromStripe);
        payment.setCustomerId(customerId);
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setPaymentStatus(status);
        payment.setPaidAt(LocalDateTime.now());

        Payment saved = paymentRepository.save(payment);
        maybePublishPaymentSuccess(saved);
        return saved;
    }

    public Payment savePaymentDirect(Payment payment) {
        payment.setPaidAt(LocalDateTime.now());
        Payment saved = paymentRepository.save(payment);
        maybePublishPaymentSuccess(saved);
        return saved;
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
        Payment saved =  paymentRepository.findById(id).map(payment -> {
            payment.setPaymentIdFromStripe(updatedPayment.getPaymentIdFromStripe());
            payment.setCustomerId(updatedPayment.getCustomerId());
            payment.setOrderId(updatedPayment.getOrderId());
            payment.setAmount(updatedPayment.getAmount());
            payment.setPaymentStatus(updatedPayment.getPaymentStatus());
            payment.setPaidAt(LocalDateTime.now());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found with id " + id));

        maybePublishPaymentSuccess(saved);
        return saved;
    }


    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

}
