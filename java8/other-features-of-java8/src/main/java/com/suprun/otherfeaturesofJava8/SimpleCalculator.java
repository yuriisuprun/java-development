package com.suprun.otherfeaturesofJava8;

/**
 * Simple implementation of Calculator interface.
 * Demonstrates implementing interface with default and static methods.
 */
public class SimpleCalculator implements Calculator {

    @Override
    public int calculate(int a, int b) {
        return add(a, b);
    }
}
