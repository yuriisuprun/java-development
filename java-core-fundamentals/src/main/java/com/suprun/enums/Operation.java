package com.suprun.enums;

/**
 * @author Yurii_Suprun
 */
public enum Operation {
    PLUS {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        public double apply(double x, double y) {
            return x - y;
        }
    };

    // Abstract method that each enum constant must implement
    public abstract double apply(double x, double y);
}