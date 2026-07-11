package com.suprun.otherfeaturesofJava8;

/**
 * Interface demonstrating default and static methods in Java 8.
 * 
 * Key features:
 * - Default methods: Allow interfaces to provide method implementations
 * - Static methods: Provide utility methods at the interface level
 * - Abstract methods: Define contracts that implementations must fulfill
 * 
 * This interface showcases:
 * 1. Contract definition through abstract method
 * 2. Reusable implementations via default methods
 * 3. Utility functions through static methods
 * 4. How implementations can override defaults selectively
 */
public interface Calculator {

    /**
     * Abstract method - must be implemented by classes.
     * Each implementation defines its own calculation strategy.
     *
     * @param a first number
     * @param b second number
     * @return result of calculation
     */
    int calculate(int a, int b);

    /**
     * Default method - provides default implementation.
     * Can be overridden by implementing classes, or used as-is.
     * Demonstrates how interfaces can now provide concrete behavior.
     *
     * @param a first number
     * @param b second number
     * @return sum of two numbers
     */
    default int add(int a, int b) {
        return a + b;
    }

    /**
     * Default method for subtraction.
     * Shared implementation across all Calculator implementations.
     *
     * @param a first number
     * @param b second number
     * @return difference of two numbers
     */
    default int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Default method for multiplication.
     *
     * @param a first number
     * @param b second number
     * @return product of two numbers
     */
    default int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Default method for division with error handling.
     * Demonstrates how default methods can include complex logic and validation.
     *
     * @param a dividend
     * @param b divisor
     * @return result of division
     * @throws ArithmeticException if divisor is zero
     */
    default int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Static method - utility method at interface level.
     * Cannot be overridden, belongs to the interface itself.
     * Static methods are useful for utility operations.
     *
     * @param a first number
     * @param b second number
     * @return absolute value of difference
     */
    static int absoluteDifference(int a, int b) {
        return Math.abs(a - b);
    }

    /**
     * Static method for power calculation.
     * Provides mathematical utility without requiring an instance.
     *
     * @param base base number
     * @param exponent exponent
     * @return base raised to power of exponent
     */
    static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Static method for maximum of two numbers.
     *
     * @param a first number
     * @param b second number
     * @return maximum of the two
     */
    static int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Static method for minimum of two numbers.
     *
     * @param a first number
     * @param b second number
     * @return minimum of the two
     */
    static int min(int a, int b) {
        return Math.min(a, b);
    }
}
