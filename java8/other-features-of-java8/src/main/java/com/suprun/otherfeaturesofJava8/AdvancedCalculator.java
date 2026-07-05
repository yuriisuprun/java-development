package com.suprun.otherfeaturesofJava8;

/**
 * Advanced calculator that overrides some default methods.
 * Demonstrates selective override of default methods.
 */
public class AdvancedCalculator implements Calculator {

    @Override
    public int calculate(int a, int b) {
        return multiply(a, b);
    }

    /**
     * Override divide method with different implementation.
     * Returns double result instead of int.
     *
     * @param a dividend
     * @param b divisor
     * @return result of division
     * @throws ArithmeticException if divisor is zero
     */
    @Override
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero not allowed");
        }
        // Integer division
        return a / b;
    }
}
