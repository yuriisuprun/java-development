package com.suprun.coding;

import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayTasks {

    public static void main(String... args) {
        Integer[] numbers1 = {1, 2, 3, 4, 5};

        int[] numbers2 = {6, 7, 8, -3, 110, 11};

        String[] names = {"Alice", "Bob", "Charlie"};

        // Test with numbers containing duplicates
        Integer[] numbersWithDuplicates = {1, 2, 2, 3, 3, 3, 4, 5, 5};

        System.out.println("=== Reverse Array ===");
        System.out.println(Arrays.toString(reverseArray(numbers1)));
        System.out.println(Arrays.toString(reverseArray(names)));

        System.out.println("\n=== Min/Max Elements ===");
        System.out.println(Arrays.toString(findMaximumAndMinimumElement(numbers2)));

        System.out.println("\n=== Bubble Sort ===");
        System.out.println(Arrays.toString(bubbleSortArray(numbers2.clone())));

        System.out.println("\n=== Unique Numbers ===");
        System.out.println(getUniqueNumbers(numbersWithDuplicates));

        System.out.println("\n=== Duplicate Numbers ===");
        System.out.println(getDuplicateNumbers(numbersWithDuplicates));
    }

    private static <T> T[] reverseArray(T[] arr) {
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

    private static int[] reverseIntArrayWithStream(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return IntStream.rangeClosed(1, array.length)
                .map(i -> array[array.length - i])
                .toArray();
    }

    private static int[] findMaximumAndMinimumElement(int[] array) {
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

    private static int[] bubbleSortArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
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

    private static List<Integer> getUniqueNumbers(Integer[] numbers) {
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

    private static List<Integer> getDuplicateNumbers(Integer[] numbers) {
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
