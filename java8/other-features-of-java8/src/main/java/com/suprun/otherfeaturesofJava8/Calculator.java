package com.suprun.otherfeaturesofJava8;

/**
 * Interface demonstrating default and static methods in Java 8.
 * Default methods allow interfaces to provide method implementations.
 * Static methods allow utility methods at the interface level.
 */
public interface Calculator {

    /**
     * Abstract method - must be implemented by classes.
     *
     * @param a first number
     * @param b second number
     * @return result of calculation
     */
    int calculate(int a, int b);

    /**
     * Default method - provides default implementation.
     * Can be overridden by implementing classes.
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
