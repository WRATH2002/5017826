package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.assembler.CustomerModelAssembler;
import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerModelAssembler customerModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<CustomerDTO>> getAllCustomers() {
        List<EntityModel<CustomerDTO>> customers = customerRepository.findAll().stream()
                .map(customerModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(customers,
                linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customerModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EntityModel<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail());
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity
                .created(linkTo(methodOn(CustomerController.class).getCustomerById(savedCustomer.getId())).toUri())
                .body(customerModelAssembler.toModel(savedCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(customerDTO.getName());
            existingCustomer.setEmail(customerDTO.getEmail());
            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return ResponseEntity.ok(customerModelAssembler.toModel(updatedCustomer));
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerRepository.findById(id).map(customer -> {
            customerRepository.delete(customer);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
