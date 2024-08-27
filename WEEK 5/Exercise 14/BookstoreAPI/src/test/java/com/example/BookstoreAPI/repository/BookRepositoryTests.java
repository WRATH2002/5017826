package com.example.bookstoreapi.repository;

import com.example.bookstoreapi.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveAndFindBook() {
        Book book = new Book(null, "Title", "Author", 10.0, "1234567890");
        Book savedBook = bookRepository.save(book);

        Book foundBook = bookRepository.findById(savedBook.getId()).orElse(null);
        assertEquals("Title", foundBook.getTitle());
        assertEquals("Author", foundBook.getAuthor());
    }

    // Add other test methods as needed
}
