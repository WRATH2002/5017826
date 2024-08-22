package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.exception.ResourceNotFoundException;
import com.example.bookstoreapi.mapper.BookMapper;
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
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Book-Added");
        return new ResponseEntity<>(bookMapper.toDTO(book), headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(
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
        List<BookDTO> bookDTOs = filteredBooks.stream().map(bookMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().headers(headers).body(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        HttpHeaders headers = new HttpHeaders();
        if (book.isPresent()) {
            headers.add("Custom-Header", "Book-Found");
            return ResponseEntity.ok().headers(headers).body(bookMapper.toDTO(book.get()));
        } else {
            throw new ResourceNotFoundException("Book with ID " + id + " not found.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<Book> existingBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        HttpHeaders headers = new HttpHeaders();
        if (existingBook.isPresent()) {
            Book updatedBook = bookMapper.toEntity(bookDTO);
            books.remove(existingBook.get());
            books.add(updatedBook);
            headers.add("Custom-Header", "Book-Updated");
            return ResponseEntity.ok().headers(headers).body(bookMapper.toDTO(updatedBook));
        } else {
            throw new ResourceNotFoundException("Book with ID " + id + " not found.");
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
            throw new ResourceNotFoundException("Book with ID " + id + " not found.");
        }
    }
}
