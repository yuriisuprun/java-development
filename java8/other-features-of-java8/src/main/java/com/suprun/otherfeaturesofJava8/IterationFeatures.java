package com.suprun.otherfeaturesofJava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Demonstrates iteration features in Java 8.
 * Includes forEach, forEach on collections and maps, Iterable enhancements.
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
     *
     * @param items the items to format
     * @param <T>   the type of items
     * @return list of formatted strings
     */
    public <T> List<String> formatListItems(List<T> items) {
        List<String> formatted = new ArrayList<>();
        items.forEach(item -> formatted.add(item.toString().toUpperCase()));
        return formatted;
    }

    /**
     * Count total characters in all strings in a list.
     *
     * @param strings the list of strings
     * @return total character count
     */
    public int countTotalCharacters(List<String> strings) {
        java.util.concurrent.atomic.AtomicInteger count = new java.util.concurrent.atomic.AtomicInteger(0);
        strings.forEach(s -> count.addAndGet(s.length()));
        return count.get();
    }

    /**
     * Filter and process list items in a single forEach.
     *
     * @param numbers the list of numbers
     * @param threshold the threshold value
     * @return list of numbers greater than threshold
     */
    public List<Integer> filterAndCollect(List<Integer> numbers, int threshold) {
        List<Integer> result = new ArrayList<>();
        numbers.forEach(n -> {
            if (n > threshold) {
                result.add(n);
            }
        });
        return result;
    }

    /**
     * Group items by their string length using forEach and map.
     *
     * @param strings the list of strings
     * @return map with string length as key and list of strings as value
     */
    public Map<Integer, List<String>> groupByLength(List<String> strings) {
        Map<Integer, List<String>> grouped = new HashMap<>();
        strings.forEach(s -> {
            int length = s.length();
            grouped.computeIfAbsent(length, k -> new ArrayList<>()).add(s);
        });
        return grouped;
    }

    /**
     * Remove items matching a predicate using removeIf.
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
     *
     * @param numbers the list of numbers
     * @param mapper  the function to map each element
     */
    public void replaceAll(List<Integer> numbers, java.util.function.UnaryOperator<Integer> mapper) {
        numbers.replaceAll(mapper);
    }

    /**
     * Iterate using Iterator with remove capability.
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
     * Demonstrate spliterator usage (Java 8 parallel processing support).
     *
     * @param items the list of items
     * @param <T>   the type of items
     * @return number of items
     */
    public <T> long countUsingSpliterator(List<T> items) {
        return items.stream().count();
    }

    /**
     * Accumulate values using forEach with side effects.
     *
     * @param numbers the list of numbers
     * @return sum of all numbers
     */
    public int sumUsingForEach(List<Integer> numbers) {
        java.util.concurrent.atomic.AtomicInteger sum = new java.util.concurrent.atomic.AtomicInteger(0);
        numbers.forEach(sum::addAndGet);
        return sum.get();
    }

    /**
     * Conditional iteration - execute action only for matching items.
     *
     * @param items     the list of items
     * @param predicate condition to match
     * @param action    action to execute for matching items
     * @param <T>       the type of items
     */
    public <T> void conditionalForEach(List<T> items, java.util.function.Predicate<T> predicate,
                                        java.util.function.Consumer<T> action) {
        items.forEach(item -> {
            if (predicate.test(item)) {
                action.accept(item);
            }
        });
    }
}
