package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        // Map DTO to entity
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobile(customerDTO.getMobile());
        customer.setAadhar(customerDTO.getAadhar());

        Customer savedCustomer = customerService.saveCustomer(customer);

        // Map entity to DTO
        customerDTO.setId(savedCustomer.getId());
        return customerDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            CustomerDTO customerDTO = new CustomerDTO();
            // Map entity to DTO
            customerDTO.setId(customer.getId());
            customerDTO.setUsername(customer.getUsername());
            customerDTO.setPassword(customer.getPassword());
            customerDTO.setFirstname(customer.getFirstname());
            customerDTO.setLastname(customer.getLastname());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setMobile(customer.getMobile());
            customerDTO.setAadhar(customer.getAadhar());
            return ResponseEntity.ok(customerDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream().map(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            // Map entity to DTO
            customerDTO.setId(customer.getId());
            customerDTO.setUsername(customer.getUsername());
            customerDTO.setPassword(customer.getPassword());
            customerDTO.setFirstname(customer.getFirstname());
            customerDTO.setLastname(customer.getLastname());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setMobile(customer.getMobile());
            customerDTO.setAadhar(customer.getAadhar());
            return customerDTO;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.getCustomerById(id).map(customer -> {
            // Map DTO to entity
            customer.setUsername(customerDTO.getUsername());
            customer.setPassword(customerDTO.getPassword());
            customer.setFirstname(customerDTO.getFirstname());
            customer.setLastname(customerDTO.getLastname());
            customer.setEmail(customerDTO.getEmail());
            customer.setMobile(customerDTO.getMobile());
            customer.setAadhar(customerDTO.getAadhar());

            Customer updatedCustomer = customerService.saveCustomer(customer);

            // Map entity to DTO
            customerDTO.setId(updatedCustomer.getId());
            return customerDTO;
        }).orElseGet(() -> {
            Customer customer = new Customer();
            // Map DTO to entity
            customer.setId(id);
            customer.setUsername(customerDTO.getUsername());
            customer.setPassword(customerDTO.getPassword());
            customer.setFirstname(customerDTO.getFirstname());
            customer.setLastname(customerDTO.getLastname());
            customer.setEmail(customerDTO.getEmail());
            customer.setMobile(customerDTO.getMobile());
            customer.setAadhar(customerDTO.getAadhar());

            Customer savedCustomer = customerService.saveCustomer(customer);

            // Map entity to DTO
            customerDTO.setId(savedCustomer.getId());
            return customerDTO;
        });
    }
}
