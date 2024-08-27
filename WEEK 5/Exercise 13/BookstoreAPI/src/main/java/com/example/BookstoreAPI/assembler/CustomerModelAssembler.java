package com.example.bookstoreapi.assembler;

import com.example.bookstoreapi.controller.CustomerController;
import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.model.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<Customer, EntityModel<CustomerDTO>> {

    public CustomerModelAssembler() {
        super(CustomerController.class, (Class<EntityModel<CustomerDTO>>)(Class<?>)EntityModel.class);
    }

    @Override
    public EntityModel<CustomerDTO> toModel(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail());

        return EntityModel.of(customerDTO,
                linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
    }
}
