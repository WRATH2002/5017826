package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookById() {
        Book book = new Book(1L, "Title", "Author", 10.0, "1234567890");
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));

        Book foundBook = bookService.getBookById(1L);
        assertEquals("Title", foundBook.getTitle());
        assertEquals("Author", foundBook.getAuthor());
    }

    @Test
    public void testCreateBook() {
        Book book = new Book(1L, "Title", "Author", 10.0, "1234567890");
        when(bookRepository.save(book)).thenReturn(book);

        Book createdBook = bookService.createBook(book);
        assertEquals("Title", createdBook.getTitle());
    }

    // Add other test methods as needed
}
