package com.suprun.algorithms;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Yurii_Suprun
 */
@AllArgsConstructor
public class Algorithms {

    /**
     * Returns a sorted array using bubble sort algorithm
     *
     * @param array to sort
     * @return a sorted array
     */
    public int[] bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j + 1] < array[j]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public int[] reverseArray(int[] numbers) {
        int temp;
        for (int i = 0; i < numbers.length / 2; i++) {
            temp = numbers[i];
            numbers[i] = numbers[numbers.length - i - 1];
            numbers[numbers.length - i - 1] = temp;
        }
        return numbers;
    }

    public void reverseArray(Integer[] numbers) {
        List<Integer> numbersList = new ArrayList<>(Arrays.asList(numbers));
        Collections.reverse(numbersList);
    }

    public int findMinInArray(int[] numbers) {
        int min = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    public <T extends Comparable<T>> T findMinInArrayGenericComparable(T[] numbers) {
        T min = numbers[0];
        for (T number : numbers) {
            if (number.compareTo(min) < 0) {
                min = number;
            }
        }
        return min;
    }

    public <T> T findMinInArrayGenericComparator(T[] numbers, Comparator<T> comparator) {
        T min = numbers[0];
        for (T number : numbers) {
            if (comparator.compare(number, min) < 0) {
                min = number;
            }
        }
        return min;
    }

    public boolean isTextPalindrome(String text) {
        if (text == null) {
            text = "";
        }
        String cleanedText = text.replaceAll("[ ,.;!]", "");
        String cleanedLowerCaseText = cleanedText.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder(cleanedLowerCaseText);
        stringBuilder.reverse();
        String reversedText = stringBuilder.toString();
        return cleanedLowerCaseText.equals(reversedText);
    }

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
}