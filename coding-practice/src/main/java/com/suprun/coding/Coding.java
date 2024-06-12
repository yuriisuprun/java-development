package com.suprun.coding;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Yurii_Suprun
 */
@AllArgsConstructor
public class Coding {

    public static void main(String[] args) {
        System.out.println("");
    }

//    private static String removeDuplicates(String str) {
//
//        StringBuilder result = new StringBuilder();
//        char previousChar = str.charAt(0);
//        result.append(previousChar);
//
//        for (int i = 1; i < str.length(); i++) {
//            char currentChar = str.charAt(i);
//            if (currentChar != previousChar) {
//                result.append(currentChar);
//                previousChar = currentChar;
//            }
//        }
//
//        return result.toString();


//        StringBuilder output = new StringBuilder();
//        char previousChar = str.charAt(0);
//        output.append(previousChar);
//
//        for (int i = 1; i < str.length(); i++) {
//            char currentChar = str.charAt(i);
//            if (currentChar != previousChar) {
//                output.append(currentChar);
//                previousChar = currentChar;
//            }
//        }
//
//        return output.toString();
//    }

    public int lengthOfLongestSubstring(String str) {

        if (str == null || str.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);

            if (map.containsKey(currentChar) && map.get(currentChar) >= left) {
                left = map.get(currentChar) + 1;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            map.put(currentChar, right);
        }

        return maxLength;


//        if (str == null || str.isEmpty()){
//            return 0;
//        }
//        Map<Character, Integer> map = new HashMap<>();
//        int maxLength = 0;
//        int leftPosition = 0;
//
//        for (int rightPosition = 0; rightPosition < str.length(); rightPosition++) {
//            char currentChar = str.charAt(rightPosition);
//
//            if (map.containsKey(currentChar) && map.get(currentChar) >= leftPosition) {
//                leftPosition = map.get(currentChar) + 1;
//            }
//            map.put(currentChar, rightPosition);
//            maxLength = Math.max(maxLength, rightPosition - leftPosition + 1);
//        }
//        return maxLength;
    }

    public int[] toSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (map.containsKey(diff)) {
                result[0] = map.get(diff);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;


//        Map<Integer, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            int diff = target - nums[i];
//
//            if (map.containsKey(diff)){
//                return new int[]{map.get(diff), i};
//            }
//
//            map.put(nums[i], i);
//        }
//
//        return new int[]{0, 0};
//    }
    }

    // Deltix interview 03.06.24
    /**
     * numbers array of size N + 1
     * numbers contains any integer in range 0...N
     * numbers might contain several duplicates
     * <p>
     * Ask any additional info you need
     *
     * @return any numbers' element that is duplicated
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

    public int getDuplicateUsingStreamApi(List<Integer> numbers) {
        Map<Integer, Integer> numberCounts = new HashMap<>();
        return numbers.stream()
                .filter(number -> {
                    int count = numberCounts.getOrDefault(number, 0);
                    numberCounts.put(number, count + 1);
                    return count > 0;
                }).findFirst().orElse(0); // Assuming 0 is returned if no duplicate is found
    }

//    public int getDuplicateWithArray(List<Integer> numbers) {
//        int[] integers = new int[numbers.size()];
//        Map<Integer, Integer> integers = new HashMap<>();
//        for (Integer number : numbers) {
//            int numberQuantity = integers.getOrDefault(number, 1);
//            if (numberQuantity > 1) {
//                return number;
//            }
//            integers.put(number, numberQuantity + 1);
//        }
//        return 0;
//    }

    public String removeDuplicates(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input string must not be null or empty.");
        }

        StringBuilder result = new StringBuilder();
        char previousChar = str.charAt(0);
        result.append(previousChar);

        for (int i = 1; i < str.length(); i++) {

            char currentChar = str.charAt(i);
            if (currentChar != previousChar) {
                result.append(currentChar);
                previousChar = currentChar;
            }
        }
        return result.toString();

    }
}