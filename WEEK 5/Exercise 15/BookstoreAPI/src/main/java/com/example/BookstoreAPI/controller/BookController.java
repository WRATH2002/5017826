package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Books", description = "API for managing books")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Get all books", description = "Fetches all books in the system")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @Operation(summary = "Get a book by ID", description = "Fetches a book by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "ID of the book to be fetched", example = "1")
            @PathVariable Long id) {
        return bookService.getBookById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a new book", description = "Creates a new book in the system")
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a book", description = "Updates an existing book")
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "ID of the book to be updated", example = "1")
            @PathVariable Long id,
            @RequestBody Book book) {
        return bookService.updateBook(id, book)
                .map(updatedBook -> new ResponseEntity<>(updatedBook, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to be deleted", example = "1")
            @PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
