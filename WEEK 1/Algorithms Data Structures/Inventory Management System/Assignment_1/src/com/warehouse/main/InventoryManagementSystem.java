package com.warehouse.main;

import com.warehouse.model.Product;
import com.warehouse.service.InventoryService;

import java.util.Scanner;

public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add");
            System.out.println("2. Update");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProduct(scanner, inventoryService);
                    break;
                case 2:
                    updateProduct(scanner, inventoryService);
                    break;
                case 3:
                    deleteProduct(scanner, inventoryService);
                    break;
                case 4:
                    inventoryService.displayInventory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addProduct(Scanner scanner, InventoryService inventoryService) {
        System.out.print("Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Product name: ");
        String productName = scanner.nextLine();

        System.out.print("Product quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Product price: ");
        double price = scanner.nextDouble();

        Product product = new Product(productId, productName, quantity, price);
        inventoryService.addProduct(product);
        System.out.println("Product Added");
    }

    private static void updateProduct(Scanner scanner, InventoryService inventoryService) {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        Product product = inventoryService.getProduct(productId);
        if (product != null) {
            System.out.print("Enter new product name (current: " + product.getProductName() + "): ");
            String productName = scanner.nextLine();

            System.out.print("Enter new product quantity (current: " + product.getQuantity() + "): ");
            int quantity = scanner.nextInt();

            System.out.print("Enter new product price (current: " + product.getPrice() + "): ");
            double price = scanner.nextDouble();

            product.setProductName(productName);
            product.setQuantity(quantity);
            product.setPrice(price);

            inventoryService.updateProduct(product);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct(Scanner scanner, InventoryService inventoryService) {
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();

        inventoryService.deleteProduct(productId);
        System.out.println("Product deleted successfully.");
    }
}
