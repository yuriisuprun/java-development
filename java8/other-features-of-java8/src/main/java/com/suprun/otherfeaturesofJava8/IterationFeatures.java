package com.suprun.otherfeaturesofJava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Demonstrates iteration features in Java 8.
 * Includes forEach method on collections, functional iteration patterns,
 * and modern Stream API usage for data transformation.
 */
public class IterationFeatures {

    /**
     * Iterate over list using forEach method with Consumer.
     *
     * @param items the list of items to iterate
     * @param <T>   the type of items
     */
    public <T> void forEachList(List<T> items) {
        items.forEach(System.out::println);
    }

    /**
     * Iterate over array using forEach method.
     *
     * @param items the array of items
     * @param <T>   the type of items
     */
    public <T> void forEachArray(T[] items) {
        Arrays.asList(items).forEach(System.out::println);
    }

    /**
     * Process each item in a list with a custom action.
     *
     * @param items  the list of items
     * @param action the action to perform on each item
     * @param <T>    the type of items
     */
    public <T> void forEachWithAction(List<T> items, java.util.function.Consumer<T> action) {
        items.forEach(action);
    }

    /**
     * Iterate over list with index information using traditional loop.
     * Index-based iteration for cases where position matters.
     *
     * @param items the list of items
     * @return list of formatted strings with index and value
     * @param <T>   the type of items
     */
    public <T> List<String> forEachWithIndex(List<T> items) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            result.add("[" + i + "] " + items.get(i));
        }
        return result;
    }

    /**
     * Iterate over map entries using forEach.
     * Demonstrates forEach with BiConsumer for key-value pairs.
     *
     * @param map the map to iterate
     * @param <K> the type of keys
     * @param <V> the type of values
     */
    public <K, V> void forEachMap(Map<K, V> map) {
        map.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    /**
     * Iterate over map keys using forEach.
     *
     * @param map the map to iterate
     * @param <K> the type of keys
     * @param <V> the type of values
     */
    public <K, V> void forEachMapKeys(Map<K, V> map) {
        map.keySet().forEach(System.out::println);
    }

    /**
     * Iterate over map values using forEach.
     *
     * @param map the map to iterate
     * @param <K> the type of keys
     * @param <V> the type of values
     */
    public <K, V> void forEachMapValues(Map<K, V> map) {
        map.values().forEach(System.out::println);
    }

    /**
     * Create a list containing formatted strings from items.
     * Demonstrates stream-based transformation.
     *
     * @param items the items to format
     * @param <T>   the type of items
     * @return list of formatted strings
     */
    public <T> List<String> formatListItems(List<T> items) {
        return items.stream()
                    .map(item -> item.toString().toUpperCase())
                    .collect(Collectors.toList());
    }

    /**
     * Count total characters in all strings in a list.
     * Uses Stream API for functional aggregation instead of side effects.
     *
     * @param strings the list of strings
     * @return total character count
     */
    public int countTotalCharacters(List<String> strings) {
        return strings.stream()
                      .mapToInt(String::length)
                      .sum();
    }

    /**
     * Filter and process list items.
     * Demonstrates stream-based filtering.
     *
     * @param numbers the list of numbers
     * @param threshold the threshold value
     * @return list of numbers greater than threshold
     */
    public List<Integer> filterAndCollect(List<Integer> numbers, int threshold) {
        return numbers.stream()
                      .filter(n -> n > threshold)
                      .collect(Collectors.toList());
    }

    /**
     * Group items by their string length using Stream.groupingBy.
     * More efficient and functional than forEach with side effects.
     *
     * @param strings the list of strings
     * @return map with string length as key and list of strings as value
     */
    public Map<Integer, List<String>> groupByLength(List<String> strings) {
        return strings.stream()
                      .collect(Collectors.groupingBy(String::length));
    }

    /**
     * Remove items matching a predicate using removeIf.
     * In-place removal using Iterable.removeIf method.
     *
     * @param items     the list of items
     * @param predicate the predicate to match items for removal
     * @param <T>       the type of items
     */
    public <T> void removeIf(List<T> items, java.util.function.Predicate<T> predicate) {
        items.removeIf(predicate);
    }

    /**
     * Replace all items matching an operation.
     * Uses replaceAll with UnaryOperator for in-place transformation.
     *
     * @param numbers the list of numbers
     * @param mapper  the function to map each element
     */
    public void replaceAll(List<Integer> numbers, java.util.function.UnaryOperator<Integer> mapper) {
        numbers.replaceAll(mapper);
    }

    /**
     * Iterate using Iterator with remove capability.
     * Traditional iterator approach for cases where removal is needed.
     *
     * @param items the list of items
     * @param <T>   the type of items
     * @return number of items processed
     */
    public <T> int iterateWithIterator(List<T> items) {
        Iterator<T> iterator = items.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    /**
     * Count items using Stream API.
     * Modern approach to counting items in a collection.
     *
     * @param items the list of items
     * @param <T>   the type of items
     * @return number of items
     */
    public <T> long countUsingSpliterator(List<T> items) {
        return items.stream().count();
    }

    /**
     * Accumulate values using Stream.reduce.
     * Functional approach to sum calculation without side effects.
     *
     * @param numbers the list of numbers
     * @return sum of all numbers
     */
    public int sumUsingForEach(List<Integer> numbers) {
        return numbers.stream()
                      .reduce(0, Integer::sum);
    }

    /**
     * Conditional iteration - execute action only for matching items.
     * Combines filtering and action execution in a functional way.
     *
     * @param items     the list of items
     * @param predicate condition to match
     * @param action    action to execute for matching items
     * @param <T>       the type of items
     */
    public <T> void conditionalForEach(List<T> items, java.util.function.Predicate<T> predicate,
                                        java.util.function.Consumer<T> action) {
        items.stream()
             .filter(predicate)
             .forEach(action);
    }
}
