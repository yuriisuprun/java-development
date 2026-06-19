package com.suprun.coding;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for list/collection manipulation operations.
 * Provides algorithms for duplicate detection, filtering, merging, and searching.
 *
 * @author Yurii_Suprun
 */
public class ListOperations {

    /**
     * Finds the first duplicate element in a list using HashMap approach.
     * Performance: O(n) time, O(n) space
     *
     * @param numbers list containing duplicates
     * @return the first duplicate found, or 0 if none found
     */
    public int getDuplicate(List<Integer> numbers) {
        Map<Integer, Integer> integers = new HashMap<>();
        for (Integer number : numbers) {
            int numberQuantity = integers.getOrDefault(number, 1);
            if (numberQuantity > 1) {
                return number;
            }
            integers.put(number, numberQuantity + 1);
        }
        return 0;
    }

    /**
     * Finds the first duplicate element using Map approach (same as getDuplicate).
     *
     * @param numbers list containing duplicates
     * @return the first duplicate found, or 0 if none found
     */
    public int getDuplicateWithMap(List<Integer> numbers) {
        Map<Integer, Integer> integersMap = new HashMap<>();
        for (Integer number : numbers) {
            int numberQuantity = integersMap.getOrDefault(number, 1);
            if (numberQuantity > 1) {
                return number;
            }
            integersMap.put(number, numberQuantity + 1);
        }
        return 0;
    }

    /**
     * Finds the first duplicate element using Stream API.
     * Performance: O(n) time, O(n) space, but with functional programming style
     *
     * @param numbers list containing duplicates
     * @return the first duplicate found, or 0 if none found
     */
    public int getDuplicateUsingStreamApi(List<Integer> numbers) {
        Map<Integer, Integer> numberCounts = new HashMap<>();
        return numbers.stream()
                .filter(n -> {
                    int count = numberCounts.getOrDefault(n, 0);
                    numberCounts.put(n, count + 1);
                    return count > 0;
                }).findFirst().orElse(0);
    }

    /**
     * Finds the first duplicate element using Array-based approach.
     *
     * @param numbers list containing duplicates
     * @return the first duplicate found
     * @throws IllegalArgumentException if no duplicate is found
     */
    public int getDuplicateWithArray(List<Integer> numbers) {
        Map<Integer, Integer> numberCount = new HashMap<>();
        for (Integer number : numbers) {
            int count = numberCount.getOrDefault(number, 0);
            if (count >= 1) {
                return number;
            }
            numberCount.put(number, count + 1);
        }
        throw new IllegalArgumentException("No duplicate is found");
    }

    /**
     * Finds all duplicate elements in a list.
     *
     * @param integers list of integers
     * @return list of all elements that appear more than once
     */
    public static List<Integer> findDuplicates(List<Integer> integers) {
        if (integers == null || integers.isEmpty()) {
            return Collections.emptyList();
        }

        return integers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Finds duplicate numbers and their counts.
     *
     * @param numbers list of integers
     * @return map of duplicate numbers to their frequencies
     */
    public static Map<Integer, Long> collectNumbers(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Filters even numbers from a list.
     *
     * @param list the list of integers
     * @return list containing only even numbers
     */
    public List<Integer> filterEvenNumbers(List<Integer> list) {
        return list.stream()
                .filter(n -> n % 2 == 0)
                .toList();
    }

    /**
     * Merges and sorts two arrays.
     *
     * @param arr1 first array
     * @param arr2 second array
     * @return merged and sorted array
     */
    public static int[] mergeSorted(int[] arr1, int[] arr2) {
        return java.util.stream.IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .sorted()
                .toArray();
    }

    /**
     * Finds the two-sum pair indices in an array that add up to a target.
     * Uses HashMap for O(n) solution.
     *
     * @param nums the array of numbers
     * @param target the target sum
     * @return array containing indices of two numbers that sum to target
     * @throws IllegalArgumentException if no pair found
     */
    public int[] toSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("Target number cannot be achieved by the sum of provided numbers in the array");
    }
}
