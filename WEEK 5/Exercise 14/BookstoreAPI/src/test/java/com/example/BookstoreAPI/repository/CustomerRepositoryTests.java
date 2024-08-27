package com.example.bookstoreapi.repository;

import com.example.bookstoreapi.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveAndFindCustomer() {
        Customer customer = new Customer(null, "John Doe", "john.doe@example.com");
        Customer savedCustomer = customerRepository.save(customer);

        Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertEquals("John Doe", foundCustomer.getName());
        assertEquals("john.doe@example.com", foundCustomer.getEmail());
    }

    // Add other test methods as needed
}
