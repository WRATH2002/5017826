package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Book-Added");
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

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

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Book-List-Retrieved");
        return ResponseEntity.ok().headers(headers).body(filteredBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        HttpHeaders headers = new HttpHeaders();
        if (book.isPresent()) {
            headers.add("Custom-Header", "Book-Found");
            return ResponseEntity.ok().headers(headers).body(book.get());
        } else {
            headers.add("Custom-Header", "Book-Not-Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        HttpHeaders headers = new HttpHeaders();
        if (existingBook.isPresent()) {
            books.remove(existingBook.get());
            books.add(updatedBook);
            headers.add("Custom-Header", "Book-Updated");
            return ResponseEntity.ok().headers(headers).body(updatedBook);
        } else {
            headers.add("Custom-Header", "Book-Not-Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(book -> book.getId().equals(id));

        HttpHeaders headers = new HttpHeaders();
        if (removed) {
            headers.add("Custom-Header", "Book-Deleted");
            return ResponseEntity.noContent().headers(headers).build();
        } else {
            headers.add("Custom-Header", "Book-Not-Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
        }
    }
}
