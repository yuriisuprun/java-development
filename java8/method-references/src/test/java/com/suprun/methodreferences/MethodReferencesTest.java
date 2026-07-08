package com.suprun.methodreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Method References.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
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
        List<Person> persons = methodReferences.demonstrateConstructorReference();
        
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
        
        String lambdaResult = methodReferences.executeStringOperation(input, str -> str.toUpperCase());
        String methodRefResult = methodReferences.executeWithMethodReference(input);
        
        assertEquals(lambdaResult, methodRefResult);
        assertEquals("TEST", methodRefResult);
    }

    @Test
    @DisplayName("Method reference with Consumer functional interface")
    void testMethodReferenceWithConsumer() {
        List<String> names = Arrays.asList("Alice", "Bob");
        Consumer<String> printer = System.out::println;
        
        assertDoesNotThrow(() -> names.forEach(printer));
    }

    @Test
    @DisplayName("Method reference with Function functional interface")
    void testMethodReferenceWithFunction() {
        List<String> words = Arrays.asList("a", "ab", "abc");
        
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
                                    .map(String::toUpperCase)
                                    .map(String::trim)
                                    .collect(Collectors.toList());
        
        assertEquals(3, result.size());
        assertTrue(result.contains("HELLO"));
        assertTrue(result.contains("WORLD"));
        assertTrue(result.contains("JAVA"));
    }

    @Test
    @DisplayName("Constructor reference with no-arg constructor")
    void testConstructorReferenceNoArg() {
        Supplier<Person> personSupplier = Person::new;
        
        Person person = personSupplier.get();
        
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
        
        List<Integer> lambdaResult = words.stream()
                                          .map(s -> s.length())
                                          .collect(Collectors.toList());
        
        List<Integer> methodRefResult = words.stream()
                                             .map(String::length)
                                             .collect(Collectors.toList());
        
        assertEquals(lambdaResult, methodRefResult);
        assertEquals(Arrays.asList(1, 2, 3), methodRefResult);
    }

    @Test
    @DisplayName("Person isAdult method reference with filter")
    void testPersonIsAdultMethodReference() {
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 17),
            new Person("Jack", 30)
        );
        
        List<Person> adults = people.stream()
                                    .filter(Person::isAdult)
                                    .collect(Collectors.toList());
        
        assertEquals(2, adults.size());
        assertTrue(adults.stream().allMatch(p -> p.getAge() >= 18));
    }

    @Test
    @DisplayName("Calculator static method reference")
    void testCalculatorStaticMethodReference() {
        int result = Calculator.applyOperation(5, 3, Calculator::sum);
        assertEquals(8, result);
        
        int product = Calculator.applyOperation(5, 3, Calculator::product);
        assertEquals(15, product);
    }

    @Test
    @DisplayName("Calculator instance method reference")
    void testCalculatorInstanceMethodReference() {
        Calculator calc = new Calculator(10);
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        boolean allPositive = numbers.stream()
                                     .allMatch(calc::isPositive);
        
        assertTrue(allPositive);
    }

    @Test
    @DisplayName("StringOperation composition with method references")
    void testStringOperationComposition() {
        StringOperation trim = String::trim;
        StringOperation upper = String::toUpperCase;
        StringOperation composed = trim.andThen(upper);
        
        String result = composed.apply("  hello  ");
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("Create person from supplier with constructor reference")
    void testCreatePersonFromSupplier() {
        Person person = methodReferences.createPersonFromSupplier(Person::new);
        
        assertNotNull(person);
        assertEquals("Unknown", person.getName());
    }

    @Test
    @DisplayName("Parse numbers with method reference")
    void testParseNumbersWithMethodReference() {
        List<String> strings = Arrays.asList("1", "2", "3");
        List<Integer> numbers = methodReferences.parseNumbers(strings, Integer::parseInt);
        
        assertEquals(Arrays.asList(1, 2, 3), numbers);
    }

    @Test
    @DisplayName("Sort strings with method reference")
    void testSortStringsWithMethodReference() {
        List<String> words = Arrays.asList("zebra", "apple", "banana");
        List<String> sorted = methodReferences.sortStrings(words);
        
        assertEquals(Arrays.asList("apple", "banana", "zebra"), sorted);
    }

    @Test
    @DisplayName("Apply BiFunction with method reference")
    void testApplyBiFunctionWithMethodReference() {
        int result = methodReferences.applyBiFunction(10, 20, Math::max);
        assertEquals(20, result);
        
        int min = methodReferences.applyBiFunction(10, 20, Math::min);
        assertEquals(10, min);
    }

    @Test
    @DisplayName("Person equals and hashCode work correctly")
    void testPersonEqualsAndHashCode() {
        Person p1 = new Person("John", 25);
        Person p2 = new Person("John", 25);
        Person p3 = new Person("Jane", 25);
        
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("Null safety in MethodReferences methods")
    void testNullSafety() {
        assertThrows(NullPointerException.class, 
            () -> methodReferences.processNames(null));
        
        assertThrows(NullPointerException.class, 
            () -> methodReferences.executeStringOperation(null, String::toUpperCase));
        
        assertThrows(NullPointerException.class, 
            () -> methodReferences.executeStringOperation("test", null));
    }
}
