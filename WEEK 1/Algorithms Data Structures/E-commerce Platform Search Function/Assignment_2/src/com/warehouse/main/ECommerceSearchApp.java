package com.ecommerce.main;

import com.ecommerce.model.Product;
import com.ecommerce.service.SearchService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ECommerceSearchApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for number of products
        System.out.print("Enter the number of products: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Product[] products = new Product[n];

        // User input for products
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for product " + (i + 1) + ":");
            System.out.print("Product ID: ");
            int productId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Product Name: ");
            String productName = scanner.nextLine();

            System.out.print("Category: ");
            String category = scanner.nextLine();

            products[i] = new Product(productId, productName, category);
        }

        // Sorting products array for binary search
        Arrays.sort(products, Comparator.comparingInt(Product::getProductId));

        // User input for search type and product ID
        System.out.println("Choose search type: 1 for Linear Search, 2 for Binary Search");
        int searchType = scanner.nextInt();

        System.out.print("Enter the Product ID to search: ");
        int productIdToSearch = scanner.nextInt();

        Product result = null;

        if (searchType == 1) {
            result = SearchService.linearSearch(products, productIdToSearch);
        } else if (searchType == 2) {
            result = SearchService.binarySearch(products, productIdToSearch);
        } else {
            System.out.println("Invalid search type.");
        }

        if (result != null) {
            System.out.println("Product found: " + result);
        } else {
            System.out.println("Product not found.");
        }

        scanner.close();
    }
}
