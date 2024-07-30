package com.library.service;

import com.library.model.Book;
import java.util.Arrays;

public class LibraryManager {
    private Book[] books;
    private int size;

    public LibraryManager(int initialCapacity) {
        books = new Book[initialCapacity];
        size = 0;
    }

    // Method to add a book
    public void addBook(Book book) {
        if (size == books.length) {
            books = Arrays.copyOf(books, size * 2);
        }
        books[size++] = book;
        Arrays.sort(books, 0, size, (b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
    }

    // Linear search by title
    public Book searchByTitleLinear(String title) {
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary search by title (assumes books array is sorted by title)
    public Book searchByTitleBinary(String title) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = title.compareToIgnoreCase(books[mid].getTitle());

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return null;
    }
}
