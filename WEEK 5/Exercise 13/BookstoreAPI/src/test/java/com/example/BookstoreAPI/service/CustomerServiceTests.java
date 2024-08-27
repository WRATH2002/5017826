package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import com.example.bookstoreapi.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer(1L, "John Doe", "john.doe@example.com");
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1L);
        assertEquals("John Doe", foundCustomer.getName());
        assertEquals("john.doe@example.com", foundCustomer.getEmail());
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer(1L, "John Doe", "john.doe@example.com");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer createdCustomer = customerService.createCustomer(customer);
        assertEquals("John Doe", createdCustomer.getName());
    }

    // Add other test methods as needed
}
