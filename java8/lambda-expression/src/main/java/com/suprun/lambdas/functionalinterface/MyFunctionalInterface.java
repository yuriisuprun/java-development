package com.suprun.lambdas.functionalinterface;

/**
 * A generic functional interface that represents a function transforming an input of type T to an output of type R.
 *
 * <p>This interface demonstrates the concept of functional interfaces in Java 8+, which can be used with lambda
 * expressions and method references. It provides composition capabilities through the {@link #andThen(MyFunctionalInterface)}
 * method, allowing for fluent chaining of transformations.
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
 * }</pre>
 *
 * @param <T> the type of the input parameter
 * @param <R> the type of the result
 * @author Yurii_Suprun
 * @version 1.0
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
     * {@code after} function to the result. This enables function composition and chaining.
     *
     * <p><b>Example:</b>
     * <pre>{@code
     *   MyFunctionalInterface<String, Integer> parseInt = Integer::parseInt;
     *   MyFunctionalInterface<Integer, Integer> square = x -> x * x;
     *   MyFunctionalInterface<String, Integer> parseAndSquare = parseInt.andThen(square);
     * }</pre>
     *
     * @param <U>   the type of the output of the after function
     * @param after the function to apply after this function
     * @return a composed function that applies this function and then the after function
     * @throws NullPointerException if after is null
     */
    default <U> MyFunctionalInterface<T, U> andThen(MyFunctionalInterface<R, U> after) {
        if (after == null) {
            throw new NullPointerException("after function cannot be null");
        }
        return x -> after.apply(apply(x));
    }

    /**
     * Returns a composed function that first applies the {@code before} function to its input,
     * and then applies this function to the result. This enables reverse function composition.
     *
     * <p><b>Example:</b>
     * <pre>{@code
     *   MyFunctionalInterface<String, Integer> square = x -> x * x;
     *   MyFunctionalInterface<Integer, String> toString = Object::toString;
     *   MyFunctionalInterface<Integer, Integer> squareThenString = square.compose(toString);
     * }</pre>
     *
     * @param <V>    the type of the input to the before function
     * @param before the function to apply before this function
     * @return a composed function that applies the before function and then this function
     * @throws NullPointerException if before is null
     */
    default <V> MyFunctionalInterface<V, R> compose(MyFunctionalInterface<V, T> before) {
        if (before == null) {
            throw new NullPointerException("before function cannot be null");
        }
        return x -> apply(before.apply(x));
    }
}
