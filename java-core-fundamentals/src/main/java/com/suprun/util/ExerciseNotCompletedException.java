package com.suprun.util;

/**
 * ExerciseNotCompletedException is a custom unchecked exception thrown when an exercise implementation is incomplete.
 * This helps developers identify which methods need implementation during development.
 *
 * @author Yurii_Suprun
 */
public class ExerciseNotCompletedException extends RuntimeException {
    /**
     * Constructs an ExerciseNotCompletedException with a predefined message.
     */
    public ExerciseNotCompletedException() {
        super("Implement this method and remove exception OR switch to branch completed if you got stuck.");
    }

    /**
     * Constructs an ExerciseNotCompletedException with a custom message.
     *
     * @param message the detail message
     */
    public ExerciseNotCompletedException(String message) {
        super(message);
    }

    /**
     * Constructs an ExerciseNotCompletedException with a message and a cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ExerciseNotCompletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
