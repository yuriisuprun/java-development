package com.suprun.coding;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Yurii_Suprun
 */
@AllArgsConstructor
public class Practice {

    public String reverseString(String string) {
        int length = string.length();
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = string.charAt(length - i - 1);
        }
        return new String(result);
    }

    public String reverseStringWithConcatenation(String string) {
        String result = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            result += string.charAt(i);
        }
        return result;
    }

    public String reverseStringWithStringBuilder(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            result.append(string.charAt(i));
        }
        return result.toString();
    }

    public List<Integer> filterEvenNumbers(List<Integer> list) {
        return list.stream()
                .filter(n -> n % 2 == 0)
                .toList();
    }

    public int maxNumber(List<Integer> list) {
        return list.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    public int lengthOfLongestSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int leftPosition = 0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int rightPosition = 0; rightPosition < str.length(); rightPosition++) {
            char currentChar = str.charAt(rightPosition);
            if (map.containsKey(currentChar) && map.get(currentChar) >= leftPosition) {
                leftPosition = rightPosition + 1;
            }
            map.put(currentChar, rightPosition);
            maxLength = Math.max(maxLength, rightPosition - leftPosition + 1);
        }
        return maxLength;
    }

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

    public boolean isPalindrome(String original) {
        StringBuilder sb = new StringBuilder(original.toLowerCase());
        String reversed = sb.reverse().toString();
        return reversed.equals(original);
    }

    public int getDuplicateWithArray(List<Integer> numbers) {
        int[] integers = new int[numbers.size()];
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

    public String removeDuplicates(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input string must not be null or empty.");
        }

        StringBuilder sb = new StringBuilder();
        char currentChar = str.charAt(0);
        sb.append(currentChar);

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != currentChar) {
                sb.append(str.charAt(i));
                currentChar = str.charAt(i);
            }
        }
        return sb.toString();


//    public static void main(String[] args) {
//        List<String> tweets = Arrays.asList("#hi hello", "hi", "#hello", "#hello", "#hello", "#hello", "#hello",
//                "#epam", "#epam", "#epam", "#epam", "#epam", "#1", "#2", "#2", "#3", "#3", "#3", "#4", "#4", "#4", "#4");
//
//        System.out.println(getMostUsedHashes(tweets));
//    }
//
//    private static Map<String, Integer> getMostUsedHashes(Collection<String> tweets) {
//        return tweets.stream()
//                .flatMap(w -> Arrays.stream(w.split(" "))) // Split by space to get words
//                .filter(w -> w.startsWith("#")) // Filter out hashtags
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Group by hashtag and count
//                .entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())) // Sort by count in descending order
//                .limit(10) // Limit to top 10
//                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().intValue(), (e1, e2) -> e1, LinkedHashMap::new)); // Collect to LinkedHashMap to maintain order
//    }

    }
}