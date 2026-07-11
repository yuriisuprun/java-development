package com.suprun.otherfeaturesofJava8;

/**
 * Utility class for validating variable arguments.
 * Consolidates common validation patterns used across var-args methods.
 * Demonstrates DRY principle and separation of concerns.
 */
public final class VarArgsValidator {

    private VarArgsValidator() {
        // Utility class - cannot be instantiated
    }

    /**
     * Validates that the var-args array is not empty.
     * Throws IllegalArgumentException if the array is empty.
     *
     * @param values the variable arguments array to validate
     * @param message the error message to use if validation fails
     * @throws IllegalArgumentException if the array is empty
     */
    public static void requireNonEmpty(int[] values, String message) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Validates that a generic var-args array is not empty.
     *
     * @param <T> the type of the array elements
     * @param values the variable arguments array to validate
     * @param message the error message to use if validation fails
     * @throws IllegalArgumentException if the array is empty or null
     */
    public static <T> void requireNonEmpty(T[] values, String message) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
