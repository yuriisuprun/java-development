package com.suprun.lambdas.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for LambdaExpressionExamples.
 *
 * <p>Tests cover all example methods demonstrating lambda expression patterns.
 *
 * @author Yurii_Suprun
 * @version 1.0
 */
class LambdaExpressionExamplesTest {

    @Test
    @DisplayName("Single-line lambda should convert string to uppercase")
    void testSingleLineLambdaExample() {
        String result = LambdaExpressionExamples.singleLineLambdaExample("hello");
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("Single-line lambda should handle uppercase input")
    void testSingleLineLambdaWithUppercaseInput() {
        String result = LambdaExpressionExamples.singleLineLambdaExample("WORLD");
        assertEquals("WORLD", result);
    }

    @Test
    @DisplayName("Single-line lambda should handle mixed case input")
    void testSingleLineLambdaWithMixedCase() {
        String result = LambdaExpressionExamples.singleLineLambdaExample("HeLLo");
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("Multi-line lambda should identify even numbers")
    void testMultiLineLambdaEven() {
        String result = LambdaExpressionExamples.multiLineLambdaExample(4);
        assertEquals("Even", result);
    }

    @Test
    @DisplayName("Multi-line lambda should identify odd numbers")
    void testMultiLineLambdaOdd() {
        String result = LambdaExpressionExamples.multiLineLambdaExample(3);
        assertEquals("Odd", result);
    }

    @Test
    @DisplayName("Multi-line lambda should identify zero as even")
    void testMultiLineLambdaZero() {
        String result = LambdaExpressionExamples.multiLineLambdaExample(0);
        assertEquals("Even", result);
    }

    @Test
    @DisplayName("Method reference should parse string to integer")
    void testMethodReferenceExample() {
        Integer result = LambdaExpressionExamples.methodReferenceExample("42");
        assertEquals(42, result);
    }

    @Test
    @DisplayName("Method reference should handle large numbers")
    void testMethodReferenceWithLargeNumber() {
        Integer result = LambdaExpressionExamples.methodReferenceExample("999999");
        assertEquals(999999, result);
    }

    @Test
    @DisplayName("Composed function should parse and square")
    void testComposeFunctionExample() {
        Integer result = LambdaExpressionExamples.composeFunctionExample("5");
        assertEquals(25, result);
    }

    @Test
    @DisplayName("Composed function should handle edge case zero")
    void testComposeFunctionExampleZero() {
        Integer result = LambdaExpressionExamples.composeFunctionExample("0");
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Composed function should handle negative numbers")
    void testComposeFunctionExampleNegative() {
        Integer result = LambdaExpressionExamples.composeFunctionExample("-3");
        assertEquals(9, result);
    }

    @Test
    @DisplayName("Compose reverse example should work correctly")
    void testComposeReverseExample() {
        String result = LambdaExpressionExamples.composeReverseExample(4);
        assertEquals("Result: 16", result);
    }

    @Test
    @DisplayName("Chained transformation should apply all transformations")
    void testChainedTransformationExample() {
        Integer result = LambdaExpressionExamples.chainedTransformationExample("5");
        assertEquals(25, result); // (5 * 3) + 10 = 25
    }

    @Test
    @DisplayName("Chained transformation should handle zero")
    void testChainedTransformationZero() {
        Integer result = LambdaExpressionExamples.chainedTransformationExample("0");
        assertEquals(10, result); // (0 * 3) + 10 = 10
    }

    @Test
    @DisplayName("Chained transformation should handle negative numbers")
    void testChainedTransformationNegative() {
        Integer result = LambdaExpressionExamples.chainedTransformationExample("-2");
        assertEquals(4, result); // (-2 * 3) + 10 = 4
    }

    @Test
    @DisplayName("Single-line lambda with empty string")
    void testSingleLineLambdaEmptyString() {
        String result = LambdaExpressionExamples.singleLineLambdaExample("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Single-line lambda with numbers")
    void testSingleLineLambdaNumbers() {
        String result = LambdaExpressionExamples.singleLineLambdaExample("123abc");
        assertEquals("123ABC", result);
    }

    @Test
    @DisplayName("Multi-line lambda with negative even number")
    void testMultiLineLambdaNegativeEven() {
        String result = LambdaExpressionExamples.multiLineLambdaExample(-4);
        assertEquals("Even", result);
    }

    @Test
    @DisplayName("Multi-line lambda with negative odd number")
    void testMultiLineLambdaNegativeOdd() {
        String result = LambdaExpressionExamples.multiLineLambdaExample(-3);
        assertEquals("Odd", result);
    }

    @Test
    @DisplayName("Method reference with negative number")
    void testMethodReferenceNegative() {
        Integer result = LambdaExpressionExamples.methodReferenceExample("-50");
        assertEquals(-50, result);
    }

    @Test
    @DisplayName("Method reference with zero")
    void testMethodReferenceZero() {
        Integer result = LambdaExpressionExamples.methodReferenceExample("0");
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Composed function edge case with one")
    void testComposeFunctionOne() {
        Integer result = LambdaExpressionExamples.composeFunctionExample("1");
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Compose reverse with negative number")
    void testComposeReverseNegative() {
        String result = LambdaExpressionExamples.composeReverseExample(-5);
        assertEquals("Result: 25", result);
    }

    @Test
    @DisplayName("Chained transformation with large input")
    void testChainedTransformationLarge() {
        Integer result = LambdaExpressionExamples.chainedTransformationExample("100");
        assertEquals(310, result); // (100 * 3) + 10 = 310
    }

    @Test
    @DisplayName("Multiple lambda applications in sequence")
    void testMultipleLambdaApplications() {
        String intermediate = LambdaExpressionExamples.singleLineLambdaExample("test");
        assertEquals("TEST", intermediate);
        
        String again = LambdaExpressionExamples.singleLineLambdaExample(intermediate);
        assertEquals("TEST", again);
    }

    @Test
    @DisplayName("Compose function with boundary value")
    void testComposeFunctionBoundary() {
        Integer result = LambdaExpressionExamples.composeFunctionExample("10");
        assertEquals(100, result);
    }

    @Test
    @DisplayName("Method reference with decimal string should throw exception")
    void testMethodReferenceInvalidNumber() {
        org.junit.jupiter.api.Assertions.assertThrows(NumberFormatException.class,
                () -> LambdaExpressionExamples.methodReferenceExample("12.5"));
    }

    @Test
    @DisplayName("Method reference with non-numeric string should throw exception")
    void testMethodReferenceNonNumeric() {
        org.junit.jupiter.api.Assertions.assertThrows(NumberFormatException.class,
                () -> LambdaExpressionExamples.methodReferenceExample("abc"));
    }

    @Test
    @DisplayName("Lambda with string containing spaces")
    void testSingleLineLambdaWithSpaces() {
        String result = LambdaExpressionExamples.singleLineLambdaExample("hello world");
        assertEquals("HELLO WORLD", result);
    }

    @Test
    @DisplayName("Chained transformation consistency")
    void testChainedTransformationConsistency() {
        Integer result1 = LambdaExpressionExamples.chainedTransformationExample("5");
        Integer result2 = LambdaExpressionExamples.chainedTransformationExample("5");
        assertEquals(result1, result2);
    }
}
