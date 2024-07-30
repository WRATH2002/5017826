package com.library.main;

import com.library.model.Book;
import com.library.service.LibraryManager;

import java.util.Scanner;

public class LibraryManagementApp {

    public static void main(String[] args) {
        // Initial setup of LibraryManager with a capacity for 10 books
        LibraryManager manager = new LibraryManager(10);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add a Book");
            System.out.println("2. Search by Title (Linear Search)");
            System.out.println("3. Search by Title (Binary Search)");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    manager.addBook(new Book(bookId, title, author));
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter title to search: ");
                    String linearSearchTitle = scanner.nextLine();
                    Book linearSearchResult = manager.searchByTitleLinear(linearSearchTitle);
                    if (linearSearchResult != null) {
                        System.out.println("Book found: " + linearSearchResult);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter title to search: ");
                    String binarySearchTitle = scanner.nextLine();
                    Book binarySearchResult = manager.searchByTitleBinary(binarySearchTitle);
                    if (binarySearchResult != null) {
                        System.out.println("Book found: " + binarySearchResult);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
