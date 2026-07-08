package com.suprun.methodreferences;

/**
 * Custom functional interface for string operations.
 * Demonstrates how method references work with custom functional interfaces.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
@FunctionalInterface
public interface StringOperation {
    /**
     * Applies a transformation to the input string.
     * 
     * @param input the input string
     * @return the transformed string
     */
    String apply(String input);

    /**
     * Returns a composed operation that first applies this operation,
     * then applies the after operation.
     * 
     * @param after the operation to apply after this one
     * @return a composed operation
     */
    default StringOperation andThen(StringOperation after) {
        return input -> after.apply(apply(input));
    }

    /**
     * Returns a composed operation that first applies the before operation,
     * then applies this operation.
     * 
     * @param before the operation to apply before this one
     * @return a composed operation
     */
    default StringOperation compose(StringOperation before) {
        return input -> apply(before.apply(input));
    }
}
