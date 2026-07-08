package com.suprun.methodreferences;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Demonstration of various method reference types in Java 8+.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public class Main {
    public static void main(String[] args) {
        MethodReferences mr = new MethodReferences();

        System.out.println("=== 1. Static Method References ===");
        List<String> words = mr.demonstrateStaticMethodReference();
        System.out.println("Uppercase words: " + words);

        System.out.println("\n=== 2. Instance Method References ===");
        mr.demonstrateInstanceMethodReference();

        System.out.println("\n=== 3. Constructor References ===");
        List<Person> people = mr.demonstrateConstructorReference();
        people.forEach(System.out::println);

        System.out.println("\n=== 4. Calculator Static Methods ===");
        int sum = Calculator.applyOperation(10, 5, Calculator::sum);
        int product = Calculator.applyOperation(10, 5, Calculator::product);
        System.out.println("Sum: " + sum + ", Product: " + product);

        System.out.println("\n=== 5. Calculator Instance Methods ===");
        Calculator calc = new Calculator(100);
        List<Integer> numbers = Arrays.asList(-5, 0, 5, 10);
        numbers.stream()
               .filter(calc::isPositive)
               .forEach(n -> System.out.println(n + " is positive"));

        System.out.println("\n=== 6. StringOperation Composition ===");
        StringOperation trim = String::trim;
        StringOperation upper = String::toUpperCase;
        StringOperation composed = trim.andThen(upper);
        System.out.println("Result: " + composed.apply("  hello world  "));

        System.out.println("\n=== 7. Person Constructor with Supplier ===");
        Supplier<Person> personSupplier = Person::new;
        Person defaultPerson = personSupplier.get();
        System.out.println("Default person: " + defaultPerson);

        System.out.println("\n=== 8. Parsing Numbers ===");
        List<String> stringNumbers = Arrays.asList("1", "2", "3", "4", "5");
        List<Integer> parsed = mr.parseNumbers(stringNumbers, Integer::parseInt);
        System.out.println("Parsed numbers: " + parsed);

        System.out.println("\n=== 9. Sorting with Method Reference ===");
        List<String> unsorted = Arrays.asList("zebra", "apple", "mango", "banana");
        List<String> sorted = mr.sortStrings(unsorted);
        System.out.println("Sorted: " + sorted);

        System.out.println("\n=== 10. BiFunction with Math Methods ===");
        int max = mr.applyBiFunction(42, 17, Math::max);
        int min = mr.applyBiFunction(42, 17, Math::min);
        System.out.println("Max: " + max + ", Min: " + min);

        System.out.println("\n=== 11. Processing Names Pipeline ===");
        List<String> rawNames = Arrays.asList("  alice  ", "bob", "", "  charlie  ");
        List<String> processed = mr.processNames(rawNames);
        System.out.println("Processed: " + processed);

        System.out.println("\n=== 12. Filter Adults ===");
        List<Person> allPeople = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 17),
            new Person("Jack", 30),
            new Person("Jill", 16)
        );
        allPeople.stream()
                 .filter(Person::isAdult)
                 .forEach(p -> System.out.println(p.getName() + " is an adult"));
    }
}
