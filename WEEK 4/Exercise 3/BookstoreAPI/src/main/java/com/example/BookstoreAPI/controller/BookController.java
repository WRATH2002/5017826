package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    // Existing methods...

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {

        List<Book> filteredBooks = books;

        if (title != null) {
            filteredBooks = filteredBooks.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (author != null) {
            filteredBooks = filteredBooks.stream()
                    .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(filteredBooks);
    }

    // Existing methods...
}
