package com.suprun.streamapi;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * Generic collection utilities consolidating common stream operations.
 * Eliminates code duplication between type-specific utility classes.
 * Demonstrates generic programming and functional interfaces.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public final class CollectionUtils {

    private CollectionUtils() {
        // Prevent instantiation
    }

    /**
     * Finds the element with maximum value according to a comparator.
     * Generic version of findHighestPaid, findOldest, etc.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to search
     * @param comparator the comparator to determine maximum
     * @return the element with maximum value
     * @throws IllegalArgumentException if collection is empty
     */
    public static <T> T findMax(Collection<T> collection, Comparator<T> comparator) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(comparator, "comparator cannot be null");
        return collection.stream()
                         .max(comparator)
                         .orElseThrow(() -> new IllegalArgumentException("collection cannot be empty"));
    }

    /**
     * Finds the element with minimum value according to a comparator.
     * Generic version of findLowestPaid, findYoungest, etc.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to search
     * @param comparator the comparator to determine minimum
     * @return the element with minimum value
     * @throws IllegalArgumentException if collection is empty
     */
    public static <T> T findMin(Collection<T> collection, Comparator<T> comparator) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(comparator, "comparator cannot be null");
        return collection.stream()
                         .min(comparator)
                         .orElseThrow(() -> new IllegalArgumentException("collection cannot be empty"));
    }

    /**
     * Sorts a collection by a comparator in ascending order.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to sort
     * @param comparator the comparator to use for sorting
     * @return sorted list in ascending order
     */
    public static <T> List<T> sortAscending(Collection<T> collection, Comparator<T> comparator) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(comparator, "comparator cannot be null");
        return collection.stream()
                         .sorted(comparator)
                         .collect(Collectors.toList());
    }

    /**
     * Sorts a collection by a comparator in descending order.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to sort
     * @param comparator the comparator to use for sorting
     * @return sorted list in descending order
     */
    public static <T> List<T> sortDescending(Collection<T> collection, Comparator<T> comparator) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(comparator, "comparator cannot be null");
        return collection.stream()
                         .sorted(comparator.reversed())
                         .collect(Collectors.toList());
    }

    /**
     * Filters collection by range on an integer-valued property.
     * Generic version of filterByMinSalary, filterByMaxAge, etc.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to filter
     * @param extractor function to extract comparable integer value
     * @param minValue minimum value (inclusive)
     * @param maxValue maximum value (inclusive)
     * @return filtered list within range
     */
    public static <T> List<T> filterByRange(Collection<T> collection, ToIntFunction<T> extractor, 
                                            int minValue, int maxValue) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(extractor, "extractor cannot be null");
        return collection.stream()
                         .filter(item -> {
                             int value = extractor.applyAsInt(item);
                             return value >= minValue && value <= maxValue;
                         })
                         .collect(Collectors.toList());
    }

    /**
     * Filters collection by minimum value on an integer-valued property.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to filter
     * @param extractor function to extract comparable integer value
     * @param minValue minimum value (inclusive)
     * @return filtered list with values >= minValue
     */
    public static <T> List<T> filterByMinValue(Collection<T> collection, ToIntFunction<T> extractor, 
                                               int minValue) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(extractor, "extractor cannot be null");
        return collection.stream()
                         .filter(item -> extractor.applyAsInt(item) >= minValue)
                         .collect(Collectors.toList());
    }

    /**
     * Filters collection by maximum value on an integer-valued property.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to filter
     * @param extractor function to extract comparable integer value
     * @param maxValue maximum value (inclusive)
     * @return filtered list with values <= maxValue
     */
    public static <T> List<T> filterByMaxValue(Collection<T> collection, ToIntFunction<T> extractor, 
                                               int maxValue) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(extractor, "extractor cannot be null");
        return collection.stream()
                         .filter(item -> extractor.applyAsInt(item) <= maxValue)
                         .collect(Collectors.toList());
    }

    /**
     * Groups collection elements by a classifier function.
     * Generic version of groupByPosition, groupByAge, etc.
     * 
     * @param <T> the type of collection elements
     * @param <K> the type of grouping keys
     * @param collection the collection to group
     * @param classifier function to extract grouping key
     * @return map with keys and lists of elements for each key
     */
    public static <T, K> Map<K, List<T>> groupBy(Collection<T> collection, Function<T, K> classifier) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(classifier, "classifier cannot be null");
        return collection.stream()
                         .collect(Collectors.groupingBy(classifier));
    }

    /**
     * Partitions collection elements by a predicate.
     * Generic version of partitionBySalary, partitionByAge, etc.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to partition
     * @param predicate the predicate to partition by
     * @return map with true/false keys and element lists for each partition
     */
    public static <T> Map<Boolean, List<T>> partition(Collection<T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");
        return collection.stream()
                         .collect(Collectors.partitioningBy(predicate));
    }

    /**
     * Transforms collection elements by mapping function.
     * 
     * @param <T> the type of source elements
     * @param <R> the type of result elements
     * @param collection the collection to transform
     * @param mapper function to transform each element
     * @return list of transformed elements
     */
    public static <T, R> List<R> map(Collection<T> collection, Function<T, R> mapper) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(mapper, "mapper cannot be null");
        return collection.stream()
                         .map(mapper)
                         .collect(Collectors.toList());
    }

    /**
     * Counts elements matching a predicate.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to count
     * @param predicate the predicate to match
     * @return count of matching elements
     */
    public static <T> long count(Collection<T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");
        return collection.stream()
                         .filter(predicate)
                         .count();
    }

    /**
     * Checks if all elements match a predicate.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to check
     * @param predicate the predicate to match
     * @return true if all elements match, false otherwise
     */
    public static <T> boolean allMatch(Collection<T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");
        return collection.stream()
                         .allMatch(predicate);
    }

    /**
     * Checks if any element matches a predicate.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to check
     * @param predicate the predicate to match
     * @return true if any element matches, false otherwise
     */
    public static <T> boolean anyMatch(Collection<T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");
        return collection.stream()
                         .anyMatch(predicate);
    }

    /**
     * Checks if no elements match a predicate.
     * 
     * @param <T> the type of collection elements
     * @param collection the collection to check
     * @param predicate the predicate to match
     * @return true if no elements match, false otherwise
     */
    public static <T> boolean noneMatch(Collection<T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");
        return collection.stream()
                         .noneMatch(predicate);
    }
}
