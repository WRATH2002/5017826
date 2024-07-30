package com.financial.forecast;

public class FinancialForecasting {

    /**
     * Recursive method to calculate future value.
     * @param initialValue The initial value or amount.
     * @param growthRate The growth rate per period.
     * @param periods Number of periods to forecast.
     * @return The future value.
     */
    public double calculateFutureValue(double initialValue, double growthRate, int periods) {

        if (periods == 0) {
            return initialValue;
        }
        return calculateFutureValue(initialValue * (1 + growthRate), growthRate, periods - 1);
    }
}
