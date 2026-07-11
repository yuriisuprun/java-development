package com.suprun.otherfeaturesofJava8;

import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates type inference and variable arguments (var-args) in Java 8.
 * Java 8 improves type inference for generic types and lambda expressions.
 * This class showcases practical var-args usage patterns with proper type inference.
 */
public class TypeInferenceVarArgs {

    /**
     * Variable arguments method - accepts zero or more arguments.
     *
     * @param numbers variable number of integers
     * @return array of the provided numbers
     */
    public int[] varArgsIntegers(int... numbers) {
        return numbers;
    }

    /**
     * Variable arguments for generic types.
     * Demonstrates type inference with generic varargs.
     *
     * @param items variable number of items
     * @param <T>   the type of items (inferred from arguments)
     * @return list of the provided items
     */
    @SafeVarargs
    public final <T> List<T> varArgsGeneric(T... items) {
        return Arrays.asList(items);
    }

    /**
     * Variable arguments with mixed types - demonstrates varargs with Object types.
     * Type inference works across different object types.
     *
     * @param objects variable number of objects
     * @return string representation of all objects
     */
    public String varArgsObjects(Object... objects) {
        return String.join(" ", 
            Arrays.stream(objects)
                  .map(Object::toString)
                  .toArray(String[]::new));
    }

    /**
     * Calculate sum of variable number of integers using stream.
     * Demonstrates functional approach with varargs.
     *
     * @param numbers variable number of integers
     * @return sum of all numbers
     */
    public int sumVarArgs(int... numbers) {
        return Arrays.stream(numbers).sum();
    }

    /**
     * Calculate product of variable number of integers.
     *
     * @param numbers variable number of integers (must have at least 1)
     * @return product of all numbers
     * @throws IllegalArgumentException if no numbers provided
     */
    public int productVarArgs(int... numbers) {
        VarArgsValidator.requireNonEmpty(numbers, "At least one number is required");
        return Arrays.stream(numbers).reduce(1, (a, b) -> a * b);
    }

    /**
     * Find maximum value from variable number of integers.
     *
     * @param numbers variable number of integers
     * @return maximum value
     * @throws IllegalArgumentException if no numbers provided
     */
    public int maxVarArgs(int... numbers) {
        VarArgsValidator.requireNonEmpty(numbers, "At least one number is required");
        return Arrays.stream(numbers).max().orElseThrow();
    }

    /**
     * Find minimum value from variable number of integers.
     *
     * @param numbers variable number of integers
     * @return minimum value
     * @throws IllegalArgumentException if no numbers provided
     */
    public int minVarArgs(int... numbers) {
        VarArgsValidator.requireNonEmpty(numbers, "At least one number is required");
        return Arrays.stream(numbers).min().orElseThrow();
    }

    /**
     * Combine multiple strings with separator using var-args.
     *
     * @param separator the separator to use between strings
     * @param strings   variable number of strings
     * @return combined string with separator
     */
    public String joinStrings(String separator, String... strings) {
        return String.join(separator, strings);
    }

    /**
     * Create a list from variable number of elements.
     * Demonstrates type inference - the type is inferred from context.
     *
     * @param items variable number of items
     * @param <T>   the type of items (inferred)
     * @return list containing the items
     */
    @SafeVarargs
    public final <T> List<T> createList(T... items) {
        return Arrays.asList(items);
    }

    /**
     * Type inference with method chaining.
     * Type parameter is inferred from the lambda expression.
     *
     * @param predicate a predicate function
     * @param items     variable number of items
     * @param <T>       the type of items (inferred)
     * @return count of items matching predicate
     */
    @SafeVarargs
    public final <T> long countMatching(java.util.function.Predicate<T> predicate, T... items) {
        return Arrays.stream(items).filter(predicate).count();
    }

    /**
     * Type inference with complex generic types.
     * Demonstrates bounded type parameter inference.
     *
     * @param values variable number of comparable values
     * @param <T>    the type extending Comparable (inferred)
     * @return true if values are sorted in ascending order
     */
    @SafeVarargs
    public final <T extends Comparable<T>> boolean isSorted(T... values) {
        if (values.length <= 1) {
            return true;
        }
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i].compareTo(values[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Flatten multiple arrays using var-args.
     * Optimized with System.arraycopy for better performance.
     *
     * @param arrays variable number of integer arrays
     * @return single flattened array
     */
    public int[] flattenArrays(int[]... arrays) {
        int totalLength = 0;
        for (int[] array : arrays) {
            totalLength += array.length;
        }

        int[] result = new int[totalLength];
        int index = 0;
        for (int[] array : arrays) {
            System.arraycopy(array, 0, result, index, array.length);
            index += array.length;
        }
        return result;
    }

    /**
     * Type inference in generics - demonstrating how Java 8 improves type inference.
     * Without explicit type parameters, the compiler infers them from context.
     *
     * @param first  first item
     * @param second second item
     * @param <T>    the type (inferred from arguments)
     * @return pair of items
     */
    public <T> Pair<T, T> createPair(T first, T second) {
        return new Pair<>(first, second);
    }

    /**
     * Simple Pair class for demonstration.
     * Showcases generic type inference with a simple data holder.
     *
     * @param <A> first type
     * @param <B> second type
     */
    public static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return "Pair{" + first + ", " + second + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return first.equals(pair.first) && second.equals(pair.second);
        }

        @Override
        public int hashCode() {
            return 31 * first.hashCode() + second.hashCode();
        }
    }
}
