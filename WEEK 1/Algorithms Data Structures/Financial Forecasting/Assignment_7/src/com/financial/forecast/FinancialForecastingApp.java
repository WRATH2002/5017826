package com.financial.forecast;
import java.util.Scanner;

public class FinancialForecastingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinancialForecasting forecasting = new FinancialForecasting();
        System.out.print("Enter initial value: ");
        double initialValue = scanner.nextDouble();
        System.out.print("Enter growth rate (as a decimal): ");
        double growthRate = scanner.nextDouble();
        System.out.print("Enter number of periods: ");
        int periods = scanner.nextInt();
        double futureValue = forecasting.calculateFutureValue(initialValue, growthRate, periods);
        System.out.printf("The future value after %d periods is: %.2f\n", periods, futureValue);
        scanner.close();
    }
}
