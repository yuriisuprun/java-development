package com.suprun.coding;

import java.util.*;

/**
 * Facade class for coding practice operations.
 * This class delegates to specialized operation classes for better separation of concerns.
 * Original monolithic methods have been refactored into domain-specific classes.
 *
 * @deprecated Use specific operation classes instead:
 * - StringOperations for string manipulation
 * - NumberOperations for number operations
 * - ListOperations for list/collection operations
 * - ArrayTasks for array operations
 *
 * @author Yurii_Suprun
 */
@Deprecated(since = "2.0", forRemoval = true)
public class Coding {

    private static final StringOperations stringOps = new StringOperations();
    private static final NumberOperations numberOps = new NumberOperations();
    private static final ListOperations listOps = new ListOperations();

    public static void main(String[] args) {
        demonstrateOperations();
    }

    /**
     * Demonstrates various operations using the refactored classes.
     */
    private static void demonstrateOperations() {
        StringOperations strOps = new StringOperations();
        NumberOperations numOps = new NumberOperations();
        ListOperations listOps = new ListOperations();

        // String operations
        System.out.println("=== String Operations ===");
        strOps.printFormattedStrings(new String[]{"4", "7", "nkjhjhjkljl", "l", "8", "kl", "oipip", "hhhsu", "6"}, 4);

        // List operations
        System.out.println("\n=== List Operations ===");
        System.out.println(listOps.filterEvenNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        System.out.println(numOps.maxNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));

        // Array operations
        System.out.println("\n=== Array Operations ===");
        int[] arr1 = {1, 5, 9};
        int[] arr2 = {4, 6, 7, 10};
        System.out.println(Arrays.toString(ListOperations.mergeSorted(arr1, arr2)));

        // Number operations
        System.out.println("\n=== Number Operations ===");
        System.out.println("count sum: " + NumberOperations.countSum(1, 2, 3, 4, 5));

        // String analysis
        String text = "Hello world! Hello Java, hello world.";
        System.out.println("unique strings: " + StringOperations.countUniqueStrings(text));

        // Duplicate detection
        List<Integer> integers = Arrays.asList(2, 4, 8, 8, 90, 20, 20, 5, 5);
        List<Integer> duplicates = ListOperations.findDuplicates(integers);
        System.out.println("duplicates: " + duplicates);

        // String manipulation
        List<String> unsortedStrings = Arrays.asList("cat", "white", "my", "flower", "home");
        List<String> sorted = StringOperations.sortStringsByLength(unsortedStrings);
        System.out.println("sorted by length: " + sorted);

        List<String> separatedStrings = Arrays.asList("a", "b", "c", "d", "e");
        String joinedStrings = StringOperations.joinStrings(separatedStrings);
        System.out.println("joined: " + joinedStrings);

        List<String> words = List.of("the", "your", "background", "picture", "way");
        String longestString = StringOperations.getLongestString(words);
        System.out.println("longest: " + longestString);

        List<Integer> numbers = List.of(2, 3, 3, 3, 4, 4, 5);
        Map<Integer, Long> collectedNumbers = ListOperations.collectNumbers(numbers);
        System.out.println("collected: " + collectedNumbers);
    }

    // Deprecated delegation methods for backward compatibility
    @Deprecated(since = "2.0")
    public String reverseStringWithConcatenation(String string) {
        return stringOps.reverseStringWithConcatenation(string);
    }

    @Deprecated(since = "2.0")
    public String reverseStringWithStringBuilder(String string) {
        return stringOps.reverseStringWithStringBuilder(string);
    }

    @Deprecated(since = "2.0")
    public List<Integer> filterEvenNumbers(List<Integer> list) {
        return listOps.filterEvenNumbers(list);
    }

    @Deprecated(since = "2.0")
    public int maxNumber(List<Integer> list) {
        return numberOps.maxNumber(list);
    }

    @Deprecated(since = "2.0")
    public int getDuplicate(List<Integer> numbers) {
        return listOps.getDuplicate(numbers);
    }

    @Deprecated(since = "2.0")
    public int getDuplicateWithMap(List<Integer> numbers) {
        return listOps.getDuplicateWithMap(numbers);
    }

    @Deprecated(since = "2.0")
    public int getDuplicateUsingStreamApi(List<Integer> numbers) {
        return listOps.getDuplicateUsingStreamApi(numbers);
    }

    @Deprecated(since = "2.0")
    public int getDuplicateWithArray(List<Integer> numbers) {
        return listOps.getDuplicateWithArray(numbers);
    }

    @Deprecated(since = "2.0")
    public String removeDuplicates(String str) {
        return stringOps.removeDuplicates(str);
    }

    @Deprecated(since = "2.0")
    public boolean isPalindrome(String original) {
        return stringOps.isPalindrome(original);
    }

    @Deprecated(since = "2.0")
    public boolean isPalindromeStringBuilder(String original) {
        return stringOps.isPalindromeStringBuilder(original);
    }

    @Deprecated(since = "2.0")
    public boolean isTextPalindrome(String text) {
        return stringOps.isTextPalindrome(text);
    }

    @Deprecated(since = "2.0")
    public int reverseInteger(int number) {
        return numberOps.reverseInteger(number);
    }

    @Deprecated(since = "2.0")
    public int reverseIntegerWithoutString(int number) {
        return numberOps.reverseIntegerWithoutString(number);
    }

    @Deprecated(since = "2.0")
    public boolean areAnagrams(String s1, String s2) {
        return stringOps.areAnagrams(s1, s2);
    }

    @Deprecated(since = "2.0")
    public boolean areAnagramsOopApproach(String s1, String s2) {
        return stringOps.areAnagramsOopApproach(s1, s2);
    }

    @Deprecated(since = "2.0")
    public boolean isAnagram(String[] words) {
        return stringOps.isAnagram(words);
    }

    @Deprecated(since = "2.0")
    public String toggleString(String input) {
        return stringOps.toggleString(input);
    }

    @Deprecated(since = "2.0")
    public String removeCharAt(String originalString, int indexToRemove) {
        return stringOps.removeCharAt(originalString, indexToRemove);
    }

    @Deprecated(since = "2.0")
    public int lengthOfLongestSubstring(String str) {
        return stringOps.lengthOfLongestSubstring(str);
    }

    @Deprecated(since = "2.0")
    public int[] toSum(int[] nums, int target) {
        return listOps.toSum(nums, target);
    }

    @Deprecated(since = "2.0")
    public List<String> getSortedHashTags(List<String> tweets) {
        return stringOps.getSortedHashTags(tweets);
    }

    @Deprecated(since = "2.0")
    public Set<String> filterUniqueHashtags() {
        return stringOps.filterUniqueHashtags();
    }

    @Deprecated(since = "2.0")
    public void printFormattedStrings(String[] strings, int columns_number) {
        stringOps.printFormattedStrings(strings, columns_number);
    }

    @Deprecated(since = "2.0")
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

    @Deprecated(since = "2.0")
    public int[] bubbleSort(int[] array) {
        return ArrayTasks.bubbleSortArray(array);
    }

    @Deprecated(since = "2.0")
    public int[] reverseArray(int[] numbers) {
        return ArrayTasks.reverseIntArray(numbers);
    }

    @Deprecated(since = "2.0")
    public int findMinInArray(int[] numbers) {
        int min = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    @Deprecated(since = "2.0")
    public <T extends Comparable<T>> T findMinInArrayGenericComparable(T[] numbers) {
        T min = numbers[0];
        for (T number : numbers) {
            if (number.compareTo(min) < 0) {
                min = number;
            }
        }
        return min;
    }

    @Deprecated(since = "2.0")
    public <T> T findMinInArrayGenericComparator(T[] numbers, java.util.Comparator<T> comparator) {
        T min = numbers[0];
        for (T number : numbers) {
            if (comparator.compare(number, min) < 0) {
                min = number;
            }
        }
        return min;
    }

    @Deprecated(since = "2.0")
    public int findMissedNumber(int[] array) {
        return numberOps.findMissedNumber(array);
    }

    @Deprecated(since = "2.0")
    public int findMissedNumberBySum(int[] array) {
        return numberOps.findMissedNumberBySum(array);
    }

    @Deprecated(since = "2.0")
    public int[] sortOnesZeros(int[] array) {
        return numberOps.sortOnesZeros(array);
    }

    @Deprecated(since = "2.0")
    public String reverseString(String string) {
        return stringOps.reverseString(string);
    }

    @Deprecated(since = "2.0")
    public List<String> getSortedHashTagsOldStyle(List<String> tweets) {
        Map<String, Integer> hashtagCountMap = new HashMap<>();

        for (String tweet : tweets) {
            String[] words = tweet.split("\\s+");
            for (String word : words) {
                if (word.startsWith("#")) {
                    String hashtag = word.toLowerCase().replaceAll("[\\p{Punct}&&[^#]]+$", "");
                    hashtagCountMap.put(hashtag, hashtagCountMap.getOrDefault(hashtag, 0) + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(hashtagCountMap.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        List<String> sortedHashtags = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            sortedHashtags.add(entry.getKey());
        }

        return sortedHashtags;
    }

    @Deprecated(since = "2.0")
    public static int[] mergeSorted(int[] arr1, int[] arr2) {
        return ListOperations.mergeSorted(arr1, arr2);
    }

    @Deprecated(since = "2.0")
    public static int countSum(int... integers) {
        return NumberOperations.countSum(integers);
    }

    @Deprecated(since = "2.0")
    public Map<String, Integer> returnPrefieldMap() {
        return Map.of("Good", 1, "Better", 2, "Nice", 3, "Really", 4, "Day", 5,
                "Weather", 6, "Moon", 7, "Home", 8, "White", 9, "Stone", 10);
    }
}