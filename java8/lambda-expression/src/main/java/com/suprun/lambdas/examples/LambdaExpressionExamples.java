package com.suprun.lambdas.examples;

import com.suprun.lambdas.functionalinterface.MyFunctionalInterface;

/**
 * Demonstrates various lambda expression patterns and use cases.
 *
 * <p>This class provides practical examples of:
 * <ul>
 *   <li>Single-line lambda expressions</li>
 *   <li>Multi-line lambda expressions</li>
 *   <li>Method references</li>
 *   <li>Function composition</li>
 * </ul>
 *
 * @author Yurii_Suprun
 * @version 1.0
 */
public class LambdaExpressionExamples {

    /**
     * Demonstrates a single-line lambda expression for string transformation.
     *
     * @param input the input string
     * @return the uppercase version of the input
     */
    public static String singleLineLambdaExample(String input) {
        MyFunctionalInterface<String, String> toUpperCase = s -> s.toUpperCase();
        return toUpperCase.apply(input);
    }

    /**
     * Demonstrates a multi-line lambda expression with conditional logic.
     *
     * @param input the input integer
     * @return "Even" if the number is even, "Odd" if it's odd
     */
    public static String multiLineLambdaExample(int input) {
        MyFunctionalInterface<Integer, String> checkEvenOdd = num -> {
            if (num % 2 == 0) {
                return "Even";
            } else {
                return "Odd";
            }
        };
        return checkEvenOdd.apply(input);
    }

    /**
     * Demonstrates method reference usage.
     *
     * @param input the input string
     * @return the integer value parsed from the string
     */
    public static Integer methodReferenceExample(String input) {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        return parseInteger.apply(input);
    }

    /**
     * Demonstrates function composition with andThen method.
     *
     * @param input the input string
     * @return the result of parsing and squaring the input
     */
    public static Integer composeFunctionExample(String input) {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        MyFunctionalInterface<String, Integer> parseAndSquare = parseInteger.andThen(x -> x * x);
        return parseAndSquare.apply(input);
    }

    /**
     * Demonstrates function composition with compose method (reverse order).
     *
     * @param input the input integer
     * @return the result of applying compose
     */
    public static String composeReverseExample(int input) {
        MyFunctionalInterface<Integer, Integer> square = x -> x * x;
        MyFunctionalInterface<String, Integer> stringToInt = Integer::parseInt;
        MyFunctionalInterface<String, Integer> composed = square.compose(stringToInt);
        return "Result: " + composed.apply(String.valueOf(input));
    }

    /**
     * Demonstrates chaining multiple transformations.
     *
     * @param input the input string
     * @return the result of applying multiple transformations
     */
    public static Integer chainedTransformationExample(String input) {
        MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
        MyFunctionalInterface<String, Integer> parseAndTriple = parseInteger.andThen(x -> x * 3);
        MyFunctionalInterface<String, Integer> parseTripleAndAdd = parseAndTriple.andThen(x -> x + 10);
        return parseTripleAndAdd.apply(input);
    }
}
