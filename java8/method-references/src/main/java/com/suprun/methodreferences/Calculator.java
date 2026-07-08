package com.suprun.methodreferences;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;

/**
 * Calculator demonstrating instance method references and functional operations.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public class Calculator {
    private int value;

    public Calculator() {
        this(0);
    }

    public Calculator(int initialValue) {
        this.value = initialValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Checks if a number is positive.
     * 
     * @param number the number to check
     * @return true if positive, false otherwise
     */
    public boolean isPositive(int number) {
        return number > 0;
    }

    /**
     * Checks if a number is even.
     * 
     * @param number the number to check
     * @return true if even, false otherwise
     */
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Adds a value to the current calculator value.
     * 
     * @param amount the amount to add
     * @return the new value
     */
    public int add(int amount) {
        this.value += amount;
        return this.value;
    }

    /**
     * Multiplies the current value by the given factor.
     * 
     * @param factor the multiplication factor
     * @return the new value
     */
    public int multiply(int factor) {
        this.value *= factor;
        return this.value;
    }

    /**
     * Applies a binary operation to two integers.
     * 
     * @param a first operand
     * @param b second operand
     * @param operation the operation to apply
     * @return the result
     */
    public static int applyOperation(int a, int b, IntBinaryOperator operation) {
        Objects.requireNonNull(operation, "operation cannot be null");
        return operation.applyAsInt(a, b);
    }

    /**
     * Static method for adding two numbers.
     * 
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public static int sum(int a, int b) {
        return a + b;
    }

    /**
     * Static method for multiplying two numbers.
     * 
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public static int product(int a, int b) {
        return a * b;
    }

    /**
     * Resets the calculator value to zero.
     */
    public void reset() {
        this.value = 0;
    }

    @Override
    public String toString() {
        return "Calculator{value=" + value + '}';
    }
}
