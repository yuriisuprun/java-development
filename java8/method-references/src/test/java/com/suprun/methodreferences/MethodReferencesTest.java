package com.suprun.methodreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Method References Tests")
class MethodReferencesTest {

    private MethodReferences methodReferences;

    @BeforeEach
    void setUp() {
        methodReferences = new MethodReferences();
    }

    @Test
    @DisplayName("Static method reference to String::toUpperCase")
    void testStaticMethodReference() {
        List<String> result = methodReferences.demonstrateStaticMethodReference();
        
        assertEquals(4, result.size());
        assertTrue(result.contains("HELLO"));
        assertTrue(result.contains("WORLD"));
        assertTrue(result.contains("JAVA"));
        assertTrue(result.contains("METHOD"));
    }

    @Test
    @DisplayName("Instance method reference using this::printName")
    void testInstanceMethodReference() {
        // This test verifies that the method can be called without throwing an exception
        assertDoesNotThrow(() -> methodReferences.demonstrateInstanceMethodReference());
    }

    @Test
    @DisplayName("Arbitrary object instance method reference")
    void testArbitraryObjectInstanceMethodReference() {
        List<String> result = methodReferences.demonstrateArbitraryObjectMethodReference();
        
        assertEquals(3, result.size());
        assertTrue(result.contains("hello"));
        assertTrue(result.contains("world"));
        assertTrue(result.contains("java"));
    }

    @Test
    @DisplayName("Constructor reference Person::new")
    void testConstructorReference() {
        List<MethodReferences.Person> persons = methodReferences.demonstrateConstructorReference();
        
        assertEquals(3, persons.size());
        assertEquals("John", persons.get(0).getName());
        assertEquals("Jane", persons.get(1).getName());
        assertEquals("Jack", persons.get(2).getName());
    }

    @Test
    @DisplayName("Static method reference with arithmetic operations")
    void testStaticMethodReferenceArithmetic() {
        int result = methodReferences.performOperation(5, 3);
        
        assertEquals(8, result);
    }

    @Test
    @DisplayName("Processing names with multiple method references")
    void testProcessNames() {
        List<String> names = Arrays.asList("  alice  ", "bob", "", "  charlie  ");
        List<String> result = methodReferences.processNames(names);
        
        assertEquals(3, result.size());
        assertTrue(result.contains("ALICE"));
        assertTrue(result.contains("BOB"));
        assertTrue(result.contains("CHARLIE"));
        assertFalse(result.contains(""));
    }

    @Test
    @DisplayName("Custom functional interface with method reference")
    void testCustomFunctionalInterfaceWithMethodReference() {
        String input = "hello world";
        String result = methodReferences.executeWithMethodReference(input);
        
        assertEquals("HELLO WORLD", result);
    }

    @Test
    @DisplayName("Custom functional interface with lambda vs method reference")
    void testCustomFunctionalInterfaceLambdaVsMethodReference() {
        String input = "test";
        
        // Using lambda expression
        String lambdaResult = methodReferences.executeStringOperation(input, str -> str.toUpperCase());
        
        // Using method reference
        String methodRefResult = methodReferences.executeWithMethodReference(input);
        
        assertEquals(lambdaResult, methodRefResult);
        assertEquals("TEST", methodRefResult);
    }

    @Test
    @DisplayName("Method reference with Consumer functional interface")
    void testMethodReferenceWithConsumer() {
        List<String> names = Arrays.asList("Alice", "Bob");
        
        // Using method reference with Consumer
        Consumer<String> printer = System.out::println;
        
        assertDoesNotThrow(() -> names.forEach(printer));
    }

    @Test
    @DisplayName("Method reference with Function functional interface")
    void testMethodReferenceWithFunction() {
        List<String> words = Arrays.asList("a", "ab", "abc");
        
        // Using method reference to String::length
        List<Integer> lengths = words.stream()
                                       .map(String::length)
                                       .collect(Collectors.toList());
        
        assertEquals(Arrays.asList(1, 2, 3), lengths);
    }

    @Test
    @DisplayName("Method reference to Integer::parseInt")
    void testMethodReferenceToIntegerParseInt() {
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
        
        List<Integer> parsed = numbers.stream()
                                       .map(Integer::parseInt)
                                       .collect(Collectors.toList());
        
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), parsed);
    }

    @Test
    @DisplayName("Method reference with sorting (String::compareTo)")
    void testMethodReferenceWithComparator() {
        List<String> words = Arrays.asList("zebra", "apple", "mango", "banana");
        
        List<String> sorted = words.stream()
                                    .sorted(String::compareTo)
                                    .collect(Collectors.toList());
        
        assertEquals(Arrays.asList("apple", "banana", "mango", "zebra"), sorted);
    }

    @Test
    @DisplayName("Method reference to equals method")
    void testMethodReferenceToEquals() {
        List<String> names = Arrays.asList("Alice", "Bob", "Alice", "Charlie");
        
        // Count how many names equal "Alice"
        long count = names.stream()
                          .filter("Alice"::equals)
                          .count();
        
        assertEquals(2, count);
    }

    @Test
    @DisplayName("Chaining multiple method references")
    void testChainingMethodReferences() {
        List<String> words = Arrays.asList("hello", "world", "java");
        
        List<String> result = words.stream()
                                    .map(String::toUpperCase)      // Method reference 1
                                    .map(String::trim)             // Method reference 2
                                    .collect(Collectors.toList());
        
        assertEquals(3, result.size());
        assertTrue(result.contains("HELLO"));
        assertTrue(result.contains("WORLD"));
        assertTrue(result.contains("JAVA"));
    }

    @Test
    @DisplayName("Constructor reference with no-arg constructor")
    void testConstructorReferenceNoArg() {
        java.util.function.Supplier<MethodReferences.Person> personSupplier = MethodReferences.Person::new;
        
        MethodReferences.Person person = personSupplier.get();
        
        assertNotNull(person);
        assertEquals("Unknown", person.getName());
        assertEquals(0, person.getAge());
    }

    @Test
    @DisplayName("Using method reference in filter operations")
    void testMethodReferenceInFilter() {
        List<String> words = Arrays.asList("", "hello", "", "world", "");
        
        List<String> nonEmpty = words.stream()
                                      .filter(java.util.function.Predicate.not(String::isEmpty))
                                      .collect(Collectors.toList());
        
        assertEquals(2, nonEmpty.size());
        assertTrue(nonEmpty.contains("hello"));
        assertTrue(nonEmpty.contains("world"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "example", "java"})
    @DisplayName("Parameterized test with method reference")
    void testParameterizedWithMethodReference(String input) {
        String result = methodReferences.executeWithMethodReference(input);
        
        assertEquals(input.toUpperCase(), result);
    }

    @Test
    @DisplayName("Method reference comparison with lambda expression")
    void testMethodReferenceVsLambda() {
        List<String> words = Arrays.asList("a", "bb", "ccc");
        
        // Using lambda
        List<Integer> lambdaResult = words.stream()
                                          .map(s -> s.length())
                                          .collect(Collectors.toList());
        
        // Using method reference
        List<Integer> methodRefResult = words.stream()
                                             .map(String::length)
                                             .collect(Collectors.toList());
        
        assertEquals(lambdaResult, methodRefResult);
        assertEquals(Arrays.asList(1, 2, 3), methodRefResult);
    }
}
