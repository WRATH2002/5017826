package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        // Logic to save customer (in-memory or persistence)
        // For now, we'll assume it's in-memory and return the customer.
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestParam String name,
                                                   @RequestParam String email,
                                                   @RequestParam String password) {
        // Logic to process form data and register the customer
        // For now, we'll return a simple success message.
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully: " + name);
    }
}
