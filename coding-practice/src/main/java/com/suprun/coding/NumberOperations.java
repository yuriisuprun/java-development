package com.suprun.coding;

import java.util.Arrays;

/**
 * Utility class for number manipulation operations.
 * Provides algorithms for integer reversal, missing number detection, and sorting.
 *
 * @author Yurii_Suprun
 */
public class NumberOperations {

    /**
     * Reverses an integer using string conversion.
     * Handles sign preservation.
     *
     * @param number the number to reverse
     * @return the reversed number
     */
    public int reverseInteger(int number) {
        String string = Integer.toString(Math.abs(number));
        StringBuilder sb = new StringBuilder(string);
        sb.reverse();
        int result = Integer.parseInt(sb.toString());
        if (number < 0) {
            result = -result;
        }
        return result;
    }

    /**
     * Reverses an integer without string conversion.
     * More efficient and handles overflow detection.
     *
     * @param number the number to reverse
     * @return the reversed number, or 0 if overflow occurs
     */
    public int reverseIntegerWithoutString(int number) {
        var reversed = 0L;
        var digit = 0;

        while (number != 0) {
            digit = number % 10;
            reversed = reversed * 10 + digit;
            number = number / 10;

            if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) reversed;
    }

    /**
     * Finds the missing number in a sorted array where exactly one number is missing.
     * Assumes the array contains consecutive integers with one gap.
     *
     * @param array the sorted array with one missing number
     * @return the missing number, or 0 if no number is missing
     */
    public int findMissedNumber(int[] array) {
        int length = array.length;
        int currentNumber = array[0];
        for (int i = 1; i < length; i++) {
            currentNumber++;
            if (currentNumber != array[i]) {
                return currentNumber;
            }
        }
        return 0;
    }

    /**
     * Finds the missing number using sum calculation.
     * Calculates the expected sum and compares with actual sum.
     *
     * @param array the sorted array with one missing number
     * @return the missing number, or 0 if no number is missing
     */
    public int findMissedNumberBySum(int[] array) {
        int first = array[0];
        int fullSum = 0;
        int actualSum = Arrays.stream(array).sum();
        for (int i = first; i <= array[array.length - 1]; i++) {
            fullSum = fullSum + first;
            first++;
        }
        if (fullSum != actualSum) {
            return fullSum - actualSum;
        }
        return 0;
    }

    /**
     * Calculates the sum of variable number of integers.
     *
     * @param integers the integers to sum
     * @return the sum of all integers
     */
    public static int countSum(int... integers) {
        return Arrays.stream(integers).sum();
    }

    /**
     * Finds the maximum number in a list.
     *
     * @param list the list of integers
     * @return the maximum number
     * @throws IllegalArgumentException if list is empty
     */
    public int maxNumber(java.util.List<Integer> list) {
        return list.stream()
                .max(java.util.Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    /**
     * Sorts zeros and ones in an array.
     * All zeros appear before all ones.
     *
     * @param array the array containing zeros and ones
     * @return sorted array with zeros before ones
     */
    public int[] sortOnesZeros(int[] array) {
        int zero_counter = 0;
        for (int number : array) {
            if (number == 0) {
                zero_counter++;
            }
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (i < zero_counter) ? 0 : 1;
        }
        return result;
    }
}
