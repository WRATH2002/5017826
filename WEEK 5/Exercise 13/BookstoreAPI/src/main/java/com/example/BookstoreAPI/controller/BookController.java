package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.exception.ResourceNotFoundException;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.assembler.BookModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final BookModelAssembler assembler;

    public BookController(BookRepository bookRepository, BookModelAssembler assembler) {
        this.bookRepository = bookRepository;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id, @RequestHeader(value = "Accept", defaultValue = "application/json") String acceptHeader) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        EntityModel<BookDTO> bookModel = assembler.toModel(book);

        if (acceptHeader.equals(MediaType.APPLICATION_XML_VALUE)) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(bookModel);
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(bookModel);
        }
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<BookDTO>>> getAllBooks(@RequestHeader(value = "Accept", defaultValue = "application/json") String acceptHeader) {
        List<EntityModel<BookDTO>> bookModels = bookRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (acceptHeader.equals(MediaType.APPLICATION_XML_VALUE)) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(bookModels);
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(bookModels);
        }
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book(null, bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice(), bookDTO.getIsbn());
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(savedBook.getId(), "application/json")).toUri())
                .body(assembler.toModel(savedBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        Book updatedBook = bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDTO.getTitle());
                    book.setAuthor(bookDTO.getAuthor());
                    book.setPrice(bookDTO.getPrice());
                    book.setIsbn(bookDTO.getIsbn());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    Book newBook = new Book(id, bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice(), bookDTO.getIsbn());
                    return bookRepository.save(newBook);
                });
        return ResponseEntity.ok(assembler.toModel(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
