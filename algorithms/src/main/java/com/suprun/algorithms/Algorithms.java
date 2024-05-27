package com.suprun.algorithms;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public int[] insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }

    public static int[] mergeSorted(int[] arr1, int[] arr2) {
//        Objects.checkIndex(4, 10);
        return IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .sorted()
                .toArray();
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 9};
        int[] arr2 = {4, 6, 7, 10};
        System.out.println(Arrays.toString(mergeSorted(arr1, arr2)));
    }

    //    You are given two strings as an input. You must check if they form an anagram.
//    public boolean areAnagram(String s1, String s2) {}
//    HEART <-> EARTH, LISTEN <-> SILENT
    public boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Long> letters1 = s1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Character, Long> letters2 = s2.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return letters1.equals(letters2);
    }

    public boolean areAnagramsOopApproach(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        return countLettersOfString(s1).equals(countLettersOfString(s2));
    }

    private Map<Character, Integer> countLettersOfString(String str) {
//        Map<Character, Integer> letters = new HashMap<>();
//        for (char c : str.toCharArray()) {
//            if (letters.containsKey(c)) {
//                letters.put(c, letters.get(c) + 1);
//            } else {
//                letters.put(c, 1);
//            }
//        }
//        return letters;
        Map<Character, Integer> letters = new HashMap<>();
        for (char c : str.toCharArray()) {
            letters.put(c, letters.getOrDefault(c, 0) + 1);
        }
        String s = "abcdefg";
        String sss = IntStream.range(0, s.length())
                .filter(i -> i != 3)
                .mapToObj(s::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining());
        return letters;
    }

    public Set<String> filterUniqueHashtags() {
        List<String> list = List.of("This JEP is #mainly for scientific #applications",
                "and it makes #floating-point operations consistently #strict.",
                "The default #floating-point operations are #strict or strictfp,",
                "both of which guarantee the same results from the #floating-point calculations on every platform.");

        return list.stream()
                .map(str -> str.split(" "))
                .flatMap(Arrays::stream)
                .filter(word -> word.startsWith("#"))
                .map(word -> {
                    if (word.endsWith(".")) {
                        return word.substring(0, word.length() - 1);
                    }
                    return word;
                })
                .collect(Collectors.toSet());
    }

//        NIO package
//        InputStream/OutputStream
//        factory pattern
//        builder pattern
//        strategy pattern
//        decorator pattern
//        concurrency
//        observer pattern
//        hibernate/spring Data
//        JSON: read/write - Jackson, mapper
//        RestController/RestTemplate
//        Unit testing: ...
//        Tasks:
//        Find substring in a String
//        Find element in sorted list
//        Count unique strings in a text
//        Invert linkedList
}