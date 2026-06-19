package com.suprun.polymorphism;

/**
 * OverloadedMethodsClass demonstrates method overloading, a form of compile-time (static) polymorphism.
 * Method overloading allows multiple methods with the same name but different parameters.
 *
 * @author Yurii_Suprun
 */
public class OverloadedMethodsClass {

    /**
     * Adds two integers and returns their sum.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Adds three integers and returns their sum.
     *
     * @param a the first integer
     * @param b the second integer
     * @param c the third integer
     * @return the sum of a, b, and c
     */
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    /**
     * Adds two doubles and returns their sum.
     *
     * @param a the first double
     * @param b the second double
     * @return the sum of a and b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Concatenates two strings and returns the result.
     *
     * @param a the first string
     * @param b the second string
     * @return the concatenation of a and b
     */
    public String add(String a, String b) {
        return a + b;
    }

    /**
     * Finds and returns the maximum of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the maximum of a and b
     */
    public int max(int a, int b) {
        return Math.max(a, b);
    }
}
