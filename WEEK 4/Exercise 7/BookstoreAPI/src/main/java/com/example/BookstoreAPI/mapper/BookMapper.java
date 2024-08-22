package com.example.bookstoreapi.mapper;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "title", target = "title")
	@Mapping(source = "author", target = "author")
	@Mapping(source = "price", target = "price")
	@Mapping(source = "isbn", target = "isbn")
	BookDTO toDTO(Book book);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "title", target = "title")
	@Mapping(source = "author", target = "author")
	@Mapping(source = "price", target = "price")
	@Mapping(source = "isbn", target = "isbn")
	Book toEntity(BookDTO bookDTO);
}
