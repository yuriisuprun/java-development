package com.suprun.polimorphism;

/**
 * @author Yurii_Suprun
 */
public class OverloadedMethodsClass {

    // Method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Method to add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method to add two doubles
    public double add(double a, double b) {
        return a + b;
    }

    // Method to concatenate two strings
    public String add(String a, String b) {
        return a + b;
    }

    // Method to find the maximum of two integers
    public int max(int a, int b) {
        return Math.max(a, b);
    }
}

