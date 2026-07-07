package com.suprun.lambdas.functionalinterface;

import java.util.Objects;

/**
 * A generic functional interface that represents a function transforming an input of type T to an output of type R.
 *
 * <p>This interface demonstrates the concept of functional interfaces in Java 8+, which can be used with lambda
 * expressions and method references. It provides composition capabilities through {@link #andThen(MyFunctionalInterface)}
 * and {@link #compose(MyFunctionalInterface)} methods, allowing for fluent chaining of transformations.
 *
 * <p><b>Comparison with java.util.function.Function:</b> This interface mirrors the behavior of Java's standard
 * {@code Function} interface with the same composition methods, serving as an educational demonstration.
 *
 * <p><b>Example usage:</b>
 * <pre>{@code
 *   // Using a method reference
 *   MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
 *
 *   // Using a lambda expression with composition
 *   MyFunctionalInterface<String, Integer> parseAndSquare = parseInteger.andThen(x -> x * x);
 *
 *   // Invoking the transformation
 *   Integer result = parseAndSquare.apply("5"); // Result: 25
 *
 *   // Using factory method
 *   MyFunctionalInterface<Integer, Integer> double_ = MyFunctionalInterface.of(x -> x * 2);
 * }</pre>
 *
 * @param <T> the type of the input parameter
 * @param <R> the type of the result
 * @author Yurii_Suprun
 * @version 2.0
 */
@FunctionalInterface
public interface MyFunctionalInterface<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the input argument
     * @return the function result
     */
    R apply(T t);

    /**
     * Returns a composed function that first applies this function to its input, and then applies the
     * {@code after} function to the result. This enables forward function composition and chaining.
     *
     * <p><b>Example:</b>
     * <pre>{@code
     *   MyFunctionalInterface<String, Integer> parseInt = Integer::parseInt;
     *   MyFunctionalInterface<Integer, Integer> square = x -> x * x;
     *   MyFunctionalInterface<String, Integer> parseAndSquare = parseInt.andThen(square);
     *   Integer result = parseAndSquare.apply("5"); // Result: 25
     * }</pre>
     *
     * @param <U>   the type of the output of the after function
     * @param after the function to apply after this function
     * @return a composed function that applies this function and then the after function
     * @throws NullPointerException if after is null
     */
    default <U> MyFunctionalInterface<T, U> andThen(MyFunctionalInterface<R, U> after) {
        Objects.requireNonNull(after, "after function cannot be null");
        return x -> after.apply(apply(x));
    }

    /**
     * Returns a composed function that first applies the {@code before} function to its input,
     * and then applies this function to the result. This enables reverse function composition.
     *
     * <p><b>Example:</b>
     * <pre>{@code
     *   MyFunctionalInterface<Integer, Integer> square = x -> x * x;
     *   MyFunctionalInterface<String, Integer> parseInteger = Integer::parseInt;
     *   MyFunctionalInterface<String, Integer> parseAndSquare = square.compose(parseInteger);
     *   Integer result = parseAndSquare.apply("5"); // Result: 25
     * }</pre>
     *
     * @param <V>    the type of the input to the before function
     * @param before the function to apply before this function
     * @return a composed function that applies the before function and then this function
     * @throws NullPointerException if before is null
     */
    default <V> MyFunctionalInterface<V, R> compose(MyFunctionalInterface<V, T> before) {
        Objects.requireNonNull(before, "before function cannot be null");
        return x -> apply(before.apply(x));
    }

    /**
     * Returns a function that always returns the same input value unchanged.
     * Useful as a no-op function in compositions or as a starting point for chains.
     *
     * <p><b>Example:</b>
     * <pre>{@code
     *   MyFunctionalInterface<Integer, Integer> identity = MyFunctionalInterface.identity();
     *   MyFunctionalInterface<Integer, Integer> addFive = identity.andThen(x -> x + 5);
     *   Integer result = addFive.apply(10); // Result: 15
     * }</pre>
     *
     * @param <T> the type of input and output
     * @return an identity function
     */
    static <T> MyFunctionalInterface<T, T> identity() {
        return t -> t;
    }

    /**
     * Factory method to wrap a function implementation. Provides a more explicit way to create instances.
     *
     * <p><b>Example:</b>
     * <pre>{@code
     *   MyFunctionalInterface<Integer, Integer> square = MyFunctionalInterface.of(x -> x * x);
     *   MyFunctionalInterface<String, Integer> parseInt = MyFunctionalInterface.of(Integer::parseInt);
     * }</pre>
     *
     * @param <T>      the type of the input parameter
     * @param <R>      the type of the result
     * @param function the function to wrap
     * @return a functional interface wrapping the provided function
     * @throws NullPointerException if function is null
     */
    static <T, R> MyFunctionalInterface<T, R> of(MyFunctionalInterface<T, R> function) {
        return Objects.requireNonNull(function, "function cannot be null");
    }
}
