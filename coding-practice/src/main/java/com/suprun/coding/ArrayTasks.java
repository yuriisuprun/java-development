package com.suprun.coding;

import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class for array manipulation operations.
 * Provides algorithms for sorting, searching, reversing, and filtering arrays.
 *
 * @author Yurii_Suprun
 */
public class ArrayTasks {

    /**
     * Reverses an array of any type using generics.
     * Modifies the array in-place.
     *
     * @param <T> the type of array elements
     * @param arr the array to reverse
     * @return the reversed array
     * @throws IllegalArgumentException if array is null or empty
     */
    public static <T> T[] reverseArray(T[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        for (int i = 0; i < arr.length / 2; i++) {
            T temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }

    /**
     * Reverses an int array using Stream API.
     *
     * @param array the array to reverse
     * @return the reversed array
     * @throws IllegalArgumentException if array is null or empty
     */
    public static int[] reverseIntArrayWithStream(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return IntStream.rangeClosed(1, array.length)
                .map(i -> array[array.length - i])
                .toArray();
    }

    /**
     * Reverses an int array in-place.
     * Modifies the array directly.
     *
     * @param numbers the array to reverse
     * @return the reversed array
     */
    public static int[] reverseIntArray(int[] numbers) {
        int temp;
        for (int i = 0; i < numbers.length / 2; i++) {
            temp = numbers[i];
            numbers[i] = numbers[numbers.length - i - 1];
            numbers[numbers.length - i - 1] = temp;
        }
        return numbers;
    }

    /**
     * Finds the minimum and maximum elements in an array.
     *
     * @param array the array to search
     * @return array containing [min, max]
     * @throws IllegalArgumentException if array is null or empty
     */
    public static int[] findMaximumAndMinimumElement(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int[] minMaxArray = new int[2];
        int min = array[0];
        int max = array[0];
        for (int i : array) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        minMaxArray[0] = min;
        minMaxArray[1] = max;
        return minMaxArray;
    }

    /**
     * Sorts an array using bubble sort algorithm.
     * Time Complexity: O(n²), Space Complexity: O(1)
     *
     * @param array the array to sort
     * @return the sorted array (returns as-is if empty)
     */
    public static int[] bubbleSortArray(int[] array) {
        if (array == null || array.length == 0) {
            return array; // Return as-is for null or empty
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * Finds all unique numbers (appearing exactly once) in an array.
     *
     * @param numbers the array of integers
     * @return list of unique numbers
     * @throws IllegalArgumentException if array is null
     */
    public static List<Integer> getUniqueNumbers(Integer[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        return Arrays.stream(numbers)
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Finds all duplicate numbers (appearing more than once) in an array.
     *
     * @param numbers the array of integers
     * @return list of duplicate numbers
     * @throws IllegalArgumentException if array is null
     */
    public static List<Integer> getDuplicateNumbers(Integer[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        return Arrays.stream(numbers)
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
