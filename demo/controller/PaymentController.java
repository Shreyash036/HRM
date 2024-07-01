package com.example.demo.controller;


import com.example.demo.entity.Payment;
import com.example.demo.model.PaymentDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OwnerService;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public PaymentDTO createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        // Map DTO to entity
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        // Set customer
        payment.setCustomer(customerService.getCustomerById(paymentDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found")));

        // Set owner
        payment.setOwner(ownerService.getOwnerById(paymentDTO.getOwnerId()).orElseThrow(() -> new RuntimeException("Owner not found")));

        Payment savedPayment = paymentService.savePayment(payment);

        // Map entity to DTO
        paymentDTO.setId(savedPayment.getId());
        return paymentDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        Optional<Payment> paymentOpt = paymentService.getPaymentById(id);
        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();
            PaymentDTO paymentDTO = new PaymentDTO();
            // Map entity to DTO
            paymentDTO.setId(payment.getId());
            paymentDTO.setAmount(payment.getAmount());
            paymentDTO.setPaymentDate(payment.getPaymentDate());
            paymentDTO.setCustomerId(payment.getCustomer().getId());
            paymentDTO.setOwnerId(payment.getOwner().getId());
            return ResponseEntity.ok(paymentDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments().stream().map(payment -> {
            PaymentDTO paymentDTO = new PaymentDTO();
            // Map entity to DTO
            paymentDTO.setId(payment.getId());
            paymentDTO.setAmount(payment.getAmount());
            paymentDTO.setPaymentDate(payment.getPaymentDate());
            paymentDTO.setCustomerId(payment.getCustomer().getId());
            paymentDTO.setOwnerId(payment.getOwner().getId());
            return paymentDTO;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }

    @PutMapping("/{id}")
    public PaymentDTO updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
        return paymentService.getPaymentById(id).map(payment -> {
            // Map DTO to entity
            payment.setAmount(paymentDTO.getAmount());
            payment.setPaymentDate(paymentDTO.getPaymentDate());

            // Set customer
            payment.setCustomer(customerService.getCustomerById(paymentDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found")));

            // Set owner
            payment.setOwner(ownerService.getOwnerById(paymentDTO.getOwnerId()).orElseThrow(() -> new RuntimeException("Owner not found")));

            Payment updatedPayment = paymentService.savePayment(payment);

            // Map entity to DTO
            paymentDTO.setId(updatedPayment.getId());
            return paymentDTO;
        }).orElseGet(() -> {
            Payment payment = new Payment();
            // Map DTO to entity
            payment.setId(id);
            payment.setAmount(paymentDTO.getAmount());
            payment.setPaymentDate(paymentDTO.getPaymentDate());

            // Set customer
            payment.setCustomer(customerService.getCustomerById(paymentDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found")));

            // Set owner
            payment.setOwner(ownerService.getOwnerById(paymentDTO.getOwnerId()).orElseThrow(() -> new RuntimeException("Owner not found")));

            Payment savedPayment = paymentService.savePayment(payment);

            // Map entity to DTO
            paymentDTO.setId(savedPayment.getId());
            return paymentDTO;
        });
    }
}
