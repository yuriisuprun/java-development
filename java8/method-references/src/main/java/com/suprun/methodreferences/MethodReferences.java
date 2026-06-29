package com.suprun.methodreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Method References in Java 8
 * 
 * Method references provide a way to refer to methods without invoking them.
 * They are a shorthand for lambda expressions that call a specific method.
 * 
 * Types of Method References:
 * 1. Reference to a static method: ClassName::staticMethodName
 * 2. Reference to an instance method of a particular object: object::instanceMethodName
 * 3. Reference to an instance method of an arbitrary object: ClassName::instanceMethodName
 * 4. Reference to a constructor: ClassName::new
 */
public class MethodReferences {

    /**
     * 1. Static Method Reference
     * References a static method from a class
     */
    public static int add(int a, int b) {
        return a + b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    /**
     * 2. Instance Method References
     * References a method on a specific object instance
     */
    private List<String> names;

    public MethodReferences() {
        this.names = new ArrayList<>();
    }

    public void printName(String name) {
        System.out.println("Name: " + name);
    }

    public String formatName(String name) {
        return ">> " + name + " <<";
    }

    /**
     * 3. Arbitrary Object Instance Method Reference
     * References a method that will be called on different objects
     */
    public static class Calculator {
        public int getValue() {
            return 42;
        }

        public boolean isPositive(int number) {
            return number > 0;
        }

        public String toString() {
            return "Calculator";
        }
    }

    /**
     * 4. Constructor Reference
     * References a constructor to create new instances
     */
    public static class Person {
        private String name;
        private int age;

        public Person() {
            this.name = "Unknown";
            this.age = 0;
        }

        public Person(String name) {
            this.name = name;
            this.age = 0;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    /**
     * Demonstrates static method references
     */
    public List<String> demonstrateStaticMethodReference() {
        List<String> words = Arrays.asList("hello", "world", "java", "method");
        
        // Using lambda expression
        // words.stream().map(word -> word.toUpperCase()).collect(Collectors.toList());
        
        // Using method reference to String::toUpperCase (static-like)
        return words.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
    }

    /**
     * Demonstrates instance method reference of a particular object
     */
    public void demonstrateInstanceMethodReference() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Using lambda expression
        // names.forEach(name -> this.printName(name));
        
        // Using method reference to instance method
        names.forEach(this::printName);
    }

    /**
     * Demonstrates arbitrary object instance method reference
     */
    public List<String> demonstrateArbitraryObjectMethodReference() {
        List<String> words = Arrays.asList("hello", "world", "java");
        
        // Using method reference to arbitrary object's method
        // This calls compareTo on each String object
        return words.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
    }

    /**
     * Demonstrates constructor references
     */
    public List<Person> demonstrateConstructorReference() {
        List<String> names = Arrays.asList("John", "Jane", "Jack");
        
        // Using constructor reference Person::new
        return names.stream()
                    .map(Person::new)
                    .collect(Collectors.toList());
    }

    /**
     * More advanced constructor reference example
     */
    public Person createPerson(String name) {
        // Using constructor reference with a Supplier-like interface
        // This would be a custom functional interface that takes two arguments
        return new Person(name, 25);
    }

    /**
     * Demonstrates static method reference with primitive operations
     */
    public int performOperation(int a, int b) {
        // Method reference to static method
        return applyOperation(a, b, MethodReferences::add);
    }

    private int applyOperation(int a, int b, java.util.function.IntBinaryOperator operator) {
        return operator.applyAsInt(a, b);
    }

    /**
     * Complex example: filtering and transforming with method references
     */
    public List<String> processNames(List<String> names) {
        return names.stream()
                    .filter(Predicate.not(String::isEmpty))  // Method reference to isEmpty
                    .map(String::trim)                       // Method reference to trim
                    .map(String::toUpperCase)                // Method reference to toUpperCase
                    .collect(Collectors.toList());
    }

    /**
     * Example with custom functional interface
     */
    @FunctionalInterface
    public interface StringOperation {
        String apply(String input);
    }

    public String executeStringOperation(String input, StringOperation operation) {
        return operation.apply(input);
    }

    public String executeWithMethodReference(String input) {
        // Using method reference instead of lambda
        return executeStringOperation(input, String::toUpperCase);
    }
}
