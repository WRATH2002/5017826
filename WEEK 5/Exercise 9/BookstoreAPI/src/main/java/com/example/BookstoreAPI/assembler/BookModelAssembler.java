package com.example.bookstoreapi.assembler;

import com.example.bookstoreapi.controller.BookController;
import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, EntityModel<BookDTO>> {

    public BookModelAssembler() {
        super(BookController.class, (Class<EntityModel<BookDTO>>)(Class<?>)EntityModel.class);
    }

    @Override
    public EntityModel<BookDTO> toModel(Book book) {
        BookDTO bookDTO = new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getIsbn());

        return EntityModel.of(bookDTO,
                linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
    }
}
