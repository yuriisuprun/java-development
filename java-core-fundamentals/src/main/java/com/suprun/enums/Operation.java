package com.suprun.enums;

/**
 * Operation enum demonstrates constant-specific method implementations.
 * Each operation defines how the apply method should be executed.
 *
 * @author Yurii_Suprun
 */
public enum Operation {
    /**
     * Addition operation.
     */
    PLUS {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    /**
     * Subtraction operation.
     */
    MINUS {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    };

    /**
     * Abstract method that each enum constant must implement.
     *
     * @param x the first operand
     * @param y the second operand
     * @return the result of the operation
     */
    public abstract double apply(double x, double y);
}