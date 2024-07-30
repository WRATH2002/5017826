package com.ecommerce.main;

import com.ecommerce.model.Order;
import com.ecommerce.service.SortService;

import java.util.Scanner;

public class ECommerceSortApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for number of orders
        System.out.print("Enter the number of orders: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Order[] orders = new Order[n];

        // User input for orders
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for order " + (i + 1) + ":");
            System.out.print("Order ID: ");
            int orderId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine();

            System.out.print("Total Price: ");
            double totalPrice = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            orders[i] = new Order(orderId, customerName, totalPrice);
        }

        // User input for sort type
        System.out.println("Choose sort type: 1 for Bubble Sort, 2 for Quick Sort");
        int sortType = scanner.nextInt();

        if (sortType == 1) {
            SortService.bubbleSort(orders);
        } else if (sortType == 2) {
            SortService.quickSort(orders, 0, orders.length - 1);
        } else {
            System.out.println("Invalid sort type.");
            scanner.close();
            return;
        }

        // Display sorted orders
        System.out.println("Sorted Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }

        scanner.close();
    }
}
