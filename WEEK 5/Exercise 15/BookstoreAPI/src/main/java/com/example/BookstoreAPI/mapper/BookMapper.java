package com.example.bookstoreapi.mapper;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "title", source = "title")
	@Mapping(target = "author", source = "author")
	@Mapping(target = "price", source = "price")
	@Mapping(target = "isbn", source = "isbn")
	BookDTO bookToBookDTO(Book book);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "title", source = "title")
	@Mapping(target = "author", source = "author")
	@Mapping(target = "price", source = "price")
	@Mapping(target = "isbn", source = "isbn")
	Book bookDTOToBook(BookDTO bookDTO);
}
