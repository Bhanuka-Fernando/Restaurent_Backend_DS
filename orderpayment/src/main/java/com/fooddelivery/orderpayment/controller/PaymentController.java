package com.fooddelivery.orderpayment.controller;

import com.fooddelivery.orderpayment.model.Payment;
import com.fooddelivery.orderpayment.repository.PaymentRepository;
import com.fooddelivery.orderpayment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @PostMapping
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePaymentDirect(payment);
    }

    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable String id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable String id, @RequestBody Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable String id) {
        paymentService.deletePayment(id);
        return "Payment with ID " + id + " has been deleted.";
    }


}
