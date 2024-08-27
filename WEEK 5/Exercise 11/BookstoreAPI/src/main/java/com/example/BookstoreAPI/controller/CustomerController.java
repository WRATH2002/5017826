package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.exception.ResourceNotFoundException;
import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import com.example.bookstoreapi.assembler.CustomerModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerModelAssembler assembler;

    public CustomerController(CustomerRepository customerRepository, CustomerModelAssembler assembler) {
        this.customerRepository = customerRepository;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id, @RequestHeader(value = "Accept", defaultValue = "application/json") String acceptHeader) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        EntityModel<CustomerDTO> customerModel = assembler.toModel(customer);

        if (acceptHeader.equals(MediaType.APPLICATION_XML_VALUE)) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(customerModel);
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(customerModel);
        }
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<CustomerDTO>>> getAllCustomers(@RequestHeader(value = "Accept", defaultValue = "application/json") String acceptHeader) {
        List<EntityModel<CustomerDTO>> customerModels = customerRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (acceptHeader.equals(MediaType.APPLICATION_XML_VALUE)) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(customerModels);
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(customerModels);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer(null, customerDTO.getName(), customerDTO.getEmail());
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(savedCustomer.getId(), "application/json")).toUri())
                .body(assembler.toModel(savedCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        Customer updatedCustomer = customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDTO.getName());
                    customer.setEmail(customerDTO.getEmail());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    Customer newCustomer = new Customer(id, customerDTO.getName(), customerDTO.getEmail());
                    return customerRepository.save(newCustomer);
                });
        return ResponseEntity.ok(assembler.toModel(updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
