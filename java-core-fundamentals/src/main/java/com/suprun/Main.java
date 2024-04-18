package com.suprun;

import com.suprun.enums.Day;
import com.suprun.interfaces.MyClass;
import com.suprun.interfaces.MyInterface;

/**
 * @author Yurii_Suprun
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Java Fundamentals!");

        // Abstract classes and interfaces
        MyClass myClass = new MyClass();
        myClass.abstractMethod(); // Output: Abstract method implementation
        myClass.defaultMethod(); // Output: Default method implementation
        MyInterface.staticMethod1(); // Output: Static method implementation

        // Nested interface usage
        MyInterface.NestedInterface nestedInterface = () -> System.out.println("Nested interface method");
        nestedInterface.nestedMethod(); // Output: Nested interface method

        // Nested class usage
        MyInterface.NestedClass nestedClass = new MyInterface.NestedClass();
        nestedClass.nestedMethod(); // Output: Nested class method

        // Enums
        Day today = Day.TUESDAY;
        switch (today) {
            case MONDAY -> System.out.println("It's Monday!");
            case TUESDAY -> System.out.println("It's Tuesday!");
            // Other cases...
            default -> System.out.println("It's some day!");
        }

        Day currentDay = Day.SUNDAY;
        System.out.println(currentDay.getDisplayName()); // Output: Sunday
    }
}