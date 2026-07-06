package com.suprun.lambdas.functionalinterface;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MyFunctionalInterface.
 *
 * <p>Tests cover:
 * <ul>
 *   <li>Basic apply functionality</li>
 *   <li>Function composition with andThen</li>
 *   <li>Function composition with compose</li>
 *   <li>Null pointer handling</li>
 *   <li>Chained compositions</li>
 * </ul>
 *
 * @author Yurii_Suprun
 * @version 1.0
 */
class MyFunctionalInterfaceTest {

    @Test
    @DisplayName("Apply should transform input correctly using method reference")
    void testApplyWithMethodReference() {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        Integer result = parseInteger.apply("42");
        assertEquals(42, result);
    }

    @Test
    @DisplayName("Apply should transform input correctly using lambda expression")
    void testApplyWithLambda() {
        MyFunctionalInterface<String, Integer> parseInteger = s -> Integer.parseInt(s);
        Integer result = parseInteger.apply("123");
        assertEquals(123, result);
    }

    @Test
    @DisplayName("AndThen should compose two functions correctly")
    void testAndThenComposition() {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        MyFunctionalInterface<String, Integer> parseAndSquare = parseInteger.andThen(x -> x * x);

        Integer result = parseAndSquare.apply("5");
        assertEquals(25, result);
    }

    @Test
    @DisplayName("AndThen should handle multiple chained compositions")
    void testAndThenMultipleChains() {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        MyFunctionalInterface<String, Integer> parseTriple = parseInteger.andThen(x -> x * 3);
        MyFunctionalInterface<String, Integer> parseTripleAdd = parseTriple.andThen(x -> x + 10);

        Integer result = parseTripleAdd.apply("5");
        assertEquals(25, result); // (5 * 3) + 10 = 25
    }

    @Test
    @DisplayName("Compose should compose two functions in reverse order")
    void testComposeComposition() {
        MyFunctionalInterface<Integer, Integer> square = x -> x * x;
        MyFunctionalInterface<String, Integer> stringToInt = Integer::parseInt;

        MyFunctionalInterface<String, Integer> composed = square.compose(stringToInt);
        Integer result = composed.apply("7");

        assertEquals(49, result); // 7 * 7 = 49
    }

    @Test
    @DisplayName("Compose should handle string transformations")
    void testComposeWithStringTransformation() {
        MyFunctionalInterface<String, String> upperCase = s -> s.toUpperCase();
        MyFunctionalInterface<String, String> addPrefix = s -> "Result: " + s;

        MyFunctionalInterface<String, String> composed = addPrefix.compose(upperCase);
        String result = composed.apply("hello");

        assertEquals("Result: HELLO", result);
    }

    @Test
    @DisplayName("AndThen should throw NullPointerException when after is null")
    void testAndThenNullPointerException() {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        assertThrows(NullPointerException.class, () -> parseInteger.andThen(null));
    }

    @Test
    @DisplayName("Compose should throw NullPointerException when before is null")
    void testComposeNullPointerException() {
        MyFunctionalInterface<Integer, Integer> square = x -> x * x;
        assertThrows(NullPointerException.class, () -> square.compose(null));
    }

    @Test
    @DisplayName("Complex composition with multiple functions should work correctly")
    void testComplexComposition() {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        MyFunctionalInterface<Integer, Integer> double_ = x -> x * 2;
        MyFunctionalInterface<Integer, Integer> addFive = x -> x + 5;
        MyFunctionalInterface<Integer, String> stringify = Object::toString;

        MyFunctionalInterface<String, String> composed = parseInteger
                .andThen(double_)
                .andThen(addFive)
                .andThen(stringify);

        String result = composed.apply("10");
        assertEquals("25", result); // (10 * 2) + 5 = 25, then convert to string
    }

    @Test
    @DisplayName("AndThen with identity function should return original value")
    void testAndThenWithIdentity() {
        MyFunctionalInterface<Integer, Integer> identity = x -> x;
        MyFunctionalInterface<Integer, Integer> plus10 = x -> x + 10;

        MyFunctionalInterface<Integer, Integer> result1 = identity.andThen(plus10);
        MyFunctionalInterface<Integer, Integer> result2 = plus10.andThen(identity);

        assertEquals(15, result1.apply(5));
        assertEquals(15, result2.apply(5));
    }

    @Test
    @DisplayName("Compose with identity function should return original value")
    void testComposeWithIdentity() {
        MyFunctionalInterface<Integer, Integer> identity = x -> x;
        MyFunctionalInterface<Integer, Integer> multiply3 = x -> x * 3;

        MyFunctionalInterface<Integer, Integer> composed = multiply3.compose(identity);
        assertEquals(15, composed.apply(5));
    }

    @Test
    @DisplayName("AndThen with NumberFormatException should propagate the exception")
    void testAndThenWithException() {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        MyFunctionalInterface<String, Integer> parseAndSquare = parseInteger.andThen(x -> x * x);

        assertThrows(NumberFormatException.class, () -> parseAndSquare.apply("not a number"));
    }
}
