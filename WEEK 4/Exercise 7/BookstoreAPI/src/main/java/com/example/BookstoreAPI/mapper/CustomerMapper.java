package com.example.bookstoreapi.mapper;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "email", target = "email")
	CustomerDTO toDTO(Customer customer);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "email", target = "email")
	Customer toEntity(CustomerDTO customerDTO);
}
