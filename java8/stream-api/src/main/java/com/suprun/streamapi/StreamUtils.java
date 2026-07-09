package com.suprun.streamapi;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility class providing common stream operations and helper methods.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public final class StreamUtils {

    private StreamUtils() {
        // Prevent instantiation
    }

    /**
     * Returns the longest string in a collection.
     * 
     * @param strings a collection of strings
     * @return the longest string, or empty string if collection is empty
     */
    public static String findLongestString(Collection<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .max(Comparator.comparingInt(String::length))
                      .orElse("");
    }

    /**
     * Returns the length of the longest string.
     * 
     * @param strings a collection of strings
     * @return the length of the longest string, or 0 if empty
     */
    public static int getLongestStringLength(Collection<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .mapToInt(String::length)
                      .max()
                      .orElse(0);
    }

    /**
     * Returns the shortest string in a collection.
     * 
     * @param strings a collection of strings
     * @return the shortest string, or empty string if collection is empty
     */
    public static String findShortestString(Collection<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .min(Comparator.comparingInt(String::length))
                      .orElse("");
    }

    /**
     * Sums the length of all strings in a collection.
     * 
     * @param strings a collection of strings
     * @return total length of all strings
     */
    public static int sumStringLengths(Collection<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .mapToInt(String::length)
                      .sum();
    }

    /**
     * Calculates the average length of strings.
     * 
     * @param strings a collection of strings
     * @return average length, or 0 if empty
     */
    public static double averageStringLength(Collection<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .mapToInt(String::length)
                      .average()
                      .orElse(0.0);
    }

    /**
     * Filters strings by minimum length.
     * 
     * @param strings a collection of strings
     * @param minLength the minimum length threshold
     * @return list of strings longer than minLength
     */
    public static List<String> filterByMinLength(Collection<String> strings, int minLength) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .filter(s -> s.length() >= minLength)
                      .collect(Collectors.toList());
    }

    /**
     * Filters strings by maximum length.
     * 
     * @param strings a collection of strings
     * @param maxLength the maximum length threshold
     * @return list of strings shorter than maxLength
     */
    public static List<String> filterByMaxLength(Collection<String> strings, int maxLength) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .filter(s -> s.length() <= maxLength)
                      .collect(Collectors.toList());
    }

    /**
     * Transforms strings by removing the first character.
     * 
     * @param strings a collection of strings
     * @return list of strings without first character
     */
    public static List<String> removeFirstCharacter(Collection<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .filter(s -> !s.isEmpty())
                      .map(s -> s.substring(1))
                      .collect(Collectors.toList());
    }

    /**
     * Transforms strings by removing the last character.
     * 
     * @param strings a collection of strings
     * @return list of strings without last character
     */
    public static List<String> removeLastCharacter(Collection<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");
        return strings.stream()
                      .filter(s -> !s.isEmpty())
                      .map(s -> s.substring(0, s.length() - 1))
                      .collect(Collectors.toList());
    }

    /**
     * Counts occurrences of a predicate match.
     * 
     * @param collection a collection
     * @param predicate the predicate to test
     * @param <T> the type of collection elements
     * @return the count of matching elements
     */
    public static <T> long count(Collection<T> collection, java.util.function.Predicate<T> predicate) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");
        return collection.stream()
                         .filter(predicate)
                         .count();
    }

    /**
     * Checks if all elements match a predicate.
     * 
     * @param collection a collection
     * @param predicate the predicate to test
     * @param <T> the type of collection elements
     * @return true if all match, false otherwise
     */
    public static <T> boolean allMatch(Collection<T> collection, java.util.function.Predicate<T> predicate) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");
        return collection.stream()
                         .allMatch(predicate);
    }

    /**
     * Checks if any element matches a predicate.
     * 
     * @param collection a collection
     * @param predicate the predicate to test
     * @param <T> the type of collection elements
     * @return true if any matches, false otherwise
     */
    public static <T> boolean anyMatch(Collection<T> collection, java.util.function.Predicate<T> predicate) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");
        return collection.stream()
                         .anyMatch(predicate);
    }
}
