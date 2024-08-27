package com.example.bookstoreapi.assembler;

import com.example.bookstoreapi.controller.BookController;
import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookModelAssembler {

    private final BookController bookController;

    public BookModelAssembler(BookController bookController) {
        this.bookController = bookController;
    }

    public EntityModel<BookDTO> toModel(Book book) {
        BookDTO bookDTO = new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getIsbn());

        EntityModel<BookDTO> bookModel = EntityModel.of(bookDTO);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(book.getId(), "application/json"))
                .withSelfRel();
        bookModel.add(selfLink);

        // Add other links if needed

        return bookModel;
    }
}
