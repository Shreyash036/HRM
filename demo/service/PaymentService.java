package com.example.demo.service;


import com.example.demo.entity.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment savePayment(Payment payment);

    Optional<Payment> getPaymentById(Long id);

    List<Payment> getAllPayments();

    void deletePayment(Long id);
}
