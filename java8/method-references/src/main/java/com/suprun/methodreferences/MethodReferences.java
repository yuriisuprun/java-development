package com.suprun.methodreferences;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Method References in Java 8+
 * 
 * <p>Method references provide a way to refer to methods without invoking them.
 * They are a shorthand for lambda expressions that call a specific method.
 * 
 * <p><b>Types of Method References:</b>
 * <ol>
 *   <li>Reference to a static method: {@code ClassName::staticMethodName}</li>
 *   <li>Reference to an instance method of a particular object: {@code object::instanceMethodName}</li>
 *   <li>Reference to an instance method of an arbitrary object: {@code ClassName::instanceMethodName}</li>
 *   <li>Reference to a constructor: {@code ClassName::new}</li>
 * </ol>
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public class MethodReferences {

    /**
     * Static method for adding two integers.
     * Demonstrates static method references.
     * 
     * @param a first operand
     * @param b second operand
     * @return sum of a and b
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Static method for multiplying two integers.
     * 
     * @param a first operand
     * @param b second operand
     * @return product of a and b
     */
    public static int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Static method for converting string to uppercase.
     * 
     * @param str the input string
     * @return uppercase string
     */
    public static String toUpperCase(String str) {
        return Objects.requireNonNull(str, "str cannot be null").toUpperCase();
    }

    /**
     * Prints a name with a prefix.
     * Used for demonstrating instance method references.
     * 
     * @param name the name to print
     */
    public void printName(String name) {
        System.out.println("Name: " + name);
    }

    /**
     * Formats a name with decorative brackets.
     * 
     * @param name the name to format
     * @return formatted name
     */
    public String formatName(String name) {
        return ">> " + Objects.requireNonNull(name, "name cannot be null") + " <<";
    }

    /**
     * Demonstrates static method references.
     * Example: {@code String::toUpperCase}
     * 
     * @return list of uppercase strings
     */
    public List<String> demonstrateStaticMethodReference() {
        List<String> words = Arrays.asList("hello", "world", "java", "method");
        
        // Lambda: words.stream().map(word -> word.toUpperCase())
        // Method reference is more concise
        return words.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
    }

    /**
     * Demonstrates instance method reference of a particular object.
     * Example: {@code this::printName}
     */
    public void demonstrateInstanceMethodReference() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Lambda: names.forEach(name -> this.printName(name))
        // Method reference: this::printName
        names.forEach(this::printName);
    }

    /**
     * Demonstrates arbitrary object instance method reference.
     * Example: {@code String::valueOf}
     * 
     * @return list of string values
     */
    public List<String> demonstrateArbitraryObjectMethodReference() {
        List<String> words = Arrays.asList("hello", "world", "java");
        
        // Method reference calls the method on each object in the stream
        return words.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
    }

    /**
     * Demonstrates constructor references.
     * Example: {@code Person::new}
     * 
     * @return list of Person objects
     */
    public List<Person> demonstrateConstructorReference() {
        List<String> names = Arrays.asList("John", "Jane", "Jack");
        
        // Lambda: names.stream().map(name -> new Person(name))
        // Method reference: Person::new
        return names.stream()
                    .map(Person::new)
                    .collect(Collectors.toList());
    }

    /**
     * Creates a person using a constructor.
     * 
     * @param name the person's name
     * @return a new Person instance
     */
    public Person createPerson(String name) {
        return new Person(name, 25);
    }

    /**
     * Demonstrates static method reference with custom operations.
     * Example: {@code MethodReferences::add}
     * 
     * @param a first operand
     * @param b second operand
     * @return result of the operation
     */
    public int performOperation(int a, int b) {
        return applyOperation(a, b, MethodReferences::add);
    }

    /**
     * Applies a binary operation to two integers.
     * 
     * @param a first operand
     * @param b second operand
     * @param operator the operation to apply
     * @return result of the operation
     */
    private int applyOperation(int a, int b, IntBinaryOperator operator) {
        Objects.requireNonNull(operator, "operator cannot be null");
        return operator.applyAsInt(a, b);
    }

    /**
     * Demonstrates chaining multiple method references in a pipeline.
     * Uses: {@code String::isEmpty}, {@code String::trim}, {@code String::toUpperCase}
     * 
     * @param names list of names to process
     * @return processed list of names
     */
    public List<String> processNames(List<String> names) {
        Objects.requireNonNull(names, "names cannot be null");
        return names.stream()
                    .filter(Predicate.not(String::isEmpty))  // Method reference to isEmpty
                    .map(String::trim)                       // Method reference to trim
                    .map(String::toUpperCase)                // Method reference to toUpperCase
                    .collect(Collectors.toList());
    }

    /**
     * Executes a string operation using the provided operation.
     * 
     * @param input the input string
     * @param operation the operation to apply
     * @return the result of the operation
     */
    public String executeStringOperation(String input, StringOperation operation) {
        Objects.requireNonNull(input, "input cannot be null");
        Objects.requireNonNull(operation, "operation cannot be null");
        return operation.apply(input);
    }

    /**
     * Demonstrates using method reference with custom functional interface.
     * Example: {@code String::toUpperCase}
     * 
     * @param input the input string
     * @return uppercase string
     */
    public String executeWithMethodReference(String input) {
        return executeStringOperation(input, String::toUpperCase);
    }

    /**
     * Demonstrates using supplier with constructor reference.
     * Example: {@code Person::new}
     * 
     * @param supplier the person supplier
     * @return a new Person instance
     */
    public Person createPersonFromSupplier(Supplier<Person> supplier) {
        Objects.requireNonNull(supplier, "supplier cannot be null");
        return supplier.get();
    }

    /**
     * Demonstrates using function with method reference for type conversion.
     * Example: {@code Integer::parseInt}
     * 
     * @param strings list of numeric strings
     * @param parser the parsing function
     * @return list of integers
     */
    public List<Integer> parseNumbers(List<String> strings, Function<String, Integer> parser) {
        Objects.requireNonNull(strings, "strings cannot be null");
        Objects.requireNonNull(parser, "parser cannot be null");
        return strings.stream()
                      .map(parser)
                      .collect(Collectors.toList());
    }

    /**
     * Demonstrates method reference with comparator.
     * Example: {@code String::compareTo}
     * 
     * @param strings list of strings to sort
     * @return sorted list
     */
    public List<String> sortStrings(List<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .sorted(String::compareTo)
                      .collect(Collectors.toList());
    }

    /**
     * Demonstrates method reference with BiFunction.
     * Example: {@code Math::max}
     * 
     * @param a first number
     * @param b second number
     * @param function the function to apply
     * @return the result
     */
    public int applyBiFunction(int a, int b, BiFunction<Integer, Integer, Integer> function) {
        Objects.requireNonNull(function, "function cannot be null");
        return function.apply(a, b);
    }
}
