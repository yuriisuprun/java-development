package com.suprun.interfaces;

/**
 * @author Yurii_Suprun
 */
public interface MyInterface {
    // Constant field
    int CONSTANT_VALUE = 10;

    // Abstract method
    void abstractMethod();

    // Default overloaded method
    default void abstractMethod(int number){
        System.out.println(number);
    }

    // Default method
    default void defaultMethod() {
        System.out.println("Default method implementation");
    }

    // Static method
    static void staticMethod1() {
        System.out.println("Static method1 implementation");
    }

    static void staticMethod2() {
        System.out.println("Static method2 implementation");
    }

    // Nested interface
    interface NestedInterface {
        void nestedMethod();
    }

    // Nested class
    class NestedClass {
        public void nestedMethod() {
            System.out.println("Nested class method");
        }
    }
}
