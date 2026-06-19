package com.suprun.coding;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class for string manipulation operations.
 * Provides various algorithms for string transformation and analysis including
 * reversal, palindrome checking, anagram detection, and character manipulation.
 *
 * @author Yurii_Suprun
 */
public class StringOperations {

    /**
     * Reverses a string using char array (most efficient approach).
     *
     * @param string the string to reverse
     * @return the reversed string
     */
    public String reverseString(String string) {
        int length = string.length();
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = string.charAt(length - i - 1);
        }
        return new String(result);
    }

    /**
     * Reverses a string using string concatenation (inefficient, for demonstration only).
     *
     * @param string the string to reverse
     * @return the reversed string
     */
    public String reverseStringWithConcatenation(String string) {
        String result = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            result += string.charAt(i);
        }
        return result;
    }

    /**
     * Reverses a string using StringBuilder (efficient and recommended).
     *
     * @param string the string to reverse
     * @return the reversed string
     */
    public String reverseStringWithStringBuilder(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            result.append(string.charAt(i));
        }
        return result.toString();
    }

    /**
     * Checks if a string is a palindrome (case-sensitive).
     *
     * @param original the string to check
     * @return true if the string is a palindrome, false otherwise
     */
    public boolean isPalindrome(String original) {
        int length = original.length();
        for (int i = 0; i < length / 2; i++) {
            if (original.charAt(i) != original.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a string is a palindrome using StringBuilder (case-insensitive).
     *
     * @param original the string to check
     * @return true if the string is a palindrome, false otherwise
     */
    public boolean isPalindromeStringBuilder(String original) {
        StringBuilder sb = new StringBuilder(original.toLowerCase());
        String reversed = sb.reverse().toString();
        return reversed.equals(original.toLowerCase());
    }

    /**
     * Checks if text is a palindrome, ignoring spaces and punctuation.
     *
     * @param text the text to check
     * @return true if the text is a palindrome, false otherwise
     */
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

    /**
     * Removes consecutive duplicate characters from a string.
     *
     * @param str the string to process
     * @return string with consecutive duplicates removed
     * @throws IllegalArgumentException if string is null or empty
     */
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
    }

    /**
     * Checks if two strings are anagrams using Stream API.
     * Anagrams contain the same characters with the same frequency.
     *
     * @param s1 first string
     * @param s2 second string
     * @return true if strings are anagrams, false otherwise
     */
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

    /**
     * Checks if two strings are anagrams using OOP approach.
     *
     * @param s1 first string
     * @param s2 second string
     * @return true if strings are anagrams, false otherwise
     */
    public boolean areAnagramsOopApproach(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        return countLettersOfString(s1).equals(countLettersOfString(s2));
    }

    /**
     * Checks if two strings are anagrams (supporting multiple strings with spaces).
     *
     * @param words array containing exactly two strings
     * @return true if strings are anagrams, false otherwise
     * @throws IllegalArgumentException if input doesn't contain exactly two strings
     */
    public boolean isAnagram(String[] words) {
        if (words == null || words.length != 2) {
            throw new IllegalArgumentException("Input must contain exactly two strings.");
        }

        String str1 = words[0];
        String str2 = words[1];

        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Strings cannot be null.");
        }

        str1 = str1.replaceAll("\\s+", "").toLowerCase();
        str2 = str2.replaceAll("\\s+", "").toLowerCase();

        if (str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Integer> charCounts = new HashMap<>();

        for (char c : str1.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        for (char c : str2.toCharArray()) {
            if (!charCounts.containsKey(c)) return false;
            charCounts.put(c, charCounts.get(c) - 1);
            if (charCounts.get(c) == 0) charCounts.remove(c);
        }

        return charCounts.isEmpty();
    }

    /**
     * Toggles the case of all alphabetic characters in a string.
     * Lowercase becomes uppercase and vice versa.
     *
     * @param input the string to toggle
     * @return string with toggled case
     */
    public String toggleString(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            if (Character.isLowerCase(c)) {
                sb.append(Character.toUpperCase(c));
            } else if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Removes the character at the specified index from the string.
     *
     * @param originalString the original string
     * @param indexToRemove  the index of the character to be removed
     * @return a new string without the character at the specified index
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public String removeCharAt(String originalString, int indexToRemove) {
        if (indexToRemove < 0 || indexToRemove >= originalString.length()) {
            throw new IllegalArgumentException("Index out of bounds: " + indexToRemove);
        }
        return IntStream.range(0, originalString.length())
                .filter(i -> i != indexToRemove)
                .mapToObj(originalString::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    /**
     * Finds the length of the longest substring without repeating characters.
     * Uses a sliding window approach with HashMap to track character positions.
     *
     * @param str the input string
     * @return the length of the longest substring without repeating characters
     */
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

    /**
     * Counts the number of unique words in a text (case-insensitive, ignoring punctuation).
     *
     * @param text the text to analyze
     * @return the number of unique words
     */
    public static int countUniqueStrings(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }

        String[] words = text
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .split("\\s+");

        Set<String> uniqueWords = new HashSet<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                uniqueWords.add(word);
            }
        }

        return uniqueWords.size();
    }

    /**
     * Sorts strings by their length.
     *
     * @param strings the list of strings to sort
     * @return list of strings sorted by length
     */
    public static List<String> sortStringsByLength(List<String> strings) {
        return strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }

    /**
     * Joins strings with a comma and space separator.
     *
     * @param strings the strings to join
     * @return the joined string
     */
    public static String joinStrings(List<String> strings) {
        return strings.stream()
                .collect(Collectors.joining(", "));
    }

    /**
     * Finds the longest string in a list.
     *
     * @param strings the list of strings
     * @return the longest string
     * @throws IllegalArgumentException if list is empty
     */
    public static String getLongestString(List<String> strings) {
        return strings.stream()
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    /**
     * Extracts unique hashtags from tweets and sorts them.
     *
     * @param tweets the list of tweets
     * @return sorted hashtags by frequency (descending)
     */
    public List<String> getSortedHashTags(List<String> tweets) {
        return tweets.stream()
                .flatMap(tweet -> Arrays.stream(tweet.split("\\s+")))
                .map(String::toLowerCase)
                .filter(word -> word.startsWith("#"))
                .map(word -> word.replaceAll("[\\p{Punct}&&[^#]]+$", ""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Filters unique hashtags from predefined tweets.
     *
     * @return set of unique hashtags
     */
    public Set<String> filterUniqueHashtags() {
        List<String> list = List.of("This JEP is #mainly for scientific #applications",
                "and it makes #floating-point operations consistently #strict.",
                "The default #floating-point operations are #strict or strictfp,",
                "both of which guarantee the same results from the #floating-point calculations on every platform.");

        return list.stream()
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .filter(s -> s.startsWith("#"))
                .map(s -> {
                    if (s.endsWith(".")) {
                        return s.substring(0, s.length() - 1);
                    }
                    return s;
                })
                .collect(Collectors.toSet());
    }

    /**
     * Prints strings formatted in columns with aligned spacing.
     *
     * @param strings the array of strings to print
     * @param columnsNumber the number of columns
     */
    public void printFormattedStrings(String[] strings, int columnsNumber) {
        // create a 2D array to hold strings arranged in columns
        String[][] columns = new String[(strings.length + columnsNumber - 1) / columnsNumber][columnsNumber];

        // fill the 2D array with strings from the input array
        for (int i = 0; i < strings.length; i++) {
            int row = i / columnsNumber;
            int col = i % columnsNumber;
            columns[row][col] = strings[i];
        }

        // find the maximum length of strings in each column
        int[] maxLengths = new int[columnsNumber];
        for (int col = 0; col < columnsNumber; col++) {
            int maxLength = 0;
            for (String[] row : columns) {
                if (row[col] != null && row[col].length() > maxLength) {
                    maxLength = row[col].length();
                }
            }
            maxLengths[col] = maxLength;
        }

        // print the formatted strings
        for (String[] row : columns) {
            for (int col = 0; col < columnsNumber; col++) {
                if (row[col] != null) {
                    System.out.printf("%-" + (maxLengths[col] + 2) + "s", row[col]);
                } else {
                    System.out.printf("%-" + (maxLengths[col] + 2) + "s", "");
                }
            }
            System.out.println();
        }
    }

    /**
     * Counts occurrences of each letter in a string.
     *
     * @param str the string to analyze
     * @return map of character counts
     */
    private Map<Character, Integer> countLettersOfString(String str) {
        Map<Character, Integer> letters = new HashMap<>();
        for (char c : str.toCharArray()) {
            letters.put(c, letters.getOrDefault(c, 0) + 1);
        }
        return letters;
    }
}
