package com.suprun.interfaces;

/**
 * MyInterface demonstrates various interface features in Java including:
 * - Abstract methods
 * - Default methods
 * - Static methods
 * - Constant fields
 * - Nested interfaces and classes
 *
 * @author Yurii_Suprun
 */
public interface MyInterface {
    // Constant field
    int CONSTANT_VALUE = 10;

    /**
     * Abstract method that must be implemented by concrete classes.
     */
    void abstractMethod();

    /**
     * Overloaded abstract method with an integer parameter.
     *
     * @param number the integer value to process
     */
    default void abstractMethod(int number) {
        System.out.println(number);
    }

    /**
     * Default method providing a concrete implementation.
     */
    default void defaultMethod() {
        System.out.println("Default method implementation");
    }

    /**
     * Static method that can be called on the interface directly.
     */
    static void staticMethod1() {
        System.out.println("Static method1 implementation");
    }

    /**
     * Another static method on the interface.
     */
    static void staticMethod2() {
        System.out.println("Static method2 implementation");
    }

    /**
     * Nested interface demonstrating interface nesting.
     */
    interface NestedInterface {
        void nestedMethod();
    }

    /**
     * Nested class demonstrating class nesting within an interface.
     */
    class NestedClass {
        /**
         * Demonstrates a method in a nested class.
         */
        public void nestedMethod() {
            System.out.println("Nested class method");
        }
    }
}
