package com.suprun.coding;

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

public class Coding {

    public static void main(String[] args) {
        Coding coding = new Coding();
        coding.printFormattedStrings(new String[]{"4", "7", "nkjhjhjkljl", "l", "8", "kl", "oipip", "hhhsu", "6"}, 4);
        coding.dontCallThisMethod();

        //
        System.out.println(coding.filterEvenNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        System.out.println(coding.maxNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));

        //
        int[] arr1 = {1, 5, 9};
        int[] arr2 = {4, 6, 7, 10};
        System.out.println(Arrays.toString(mergeSorted(arr1, arr2)));
    }

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

    public List<String> getSortedHashTags(List<String> tweets) {
        return tweets.stream()
                .flatMap(tweet -> Arrays.stream(tweet.split("\\s+"))) // Split by whitespace and flatten the stream
                .map(String::toLowerCase)
                .filter(word -> word.startsWith("#"))
                .map(word -> word.replaceAll("[\\p{Punct}&&[^#]]+$", "")) // Remove punctuation from the end
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList();
    }

//    public List<String> getSortedHashTagsOldStyleSolution(List<String> tweets) {
//
//    }

    public void printFormattedStrings(String[] strings, int columns_number) {
        // Create a 2D array to hold strings arranged in columns
        String[][] columns = new String[(strings.length + columns_number - 1) / columns_number][columns_number];

        // Fill the 2D array with strings from the input array
        for (int i = 0; i < strings.length; i++) {
            int row = i / columns_number;
            int col = i % columns_number;
            columns[row][col] = strings[i];
        }

        // Find the maximum length of strings in each column
        int[] maxLengths = new int[columns_number];
        for (int col = 0; col < columns_number; col++) {
            int maxLength = 0;
            for (String[] row : columns) {
                if (row[col] != null && row[col].length() > maxLength) {
                    maxLength = row[col].length();
                }
            }
            maxLengths[col] = maxLength;
        }

        // Print the formatted strings
        for (String[] row : columns) {
            for (int col = 0; col < columns_number; col++) {
                if (row[col] != null) {
                    System.out.printf("%-" + (maxLengths[col] + 2) + "s", row[col]);
                } else {
                    System.out.printf("%-" + (maxLengths[col] + 2) + "s", "");
                }
            }
            System.out.println();
        }
    }

    public void dontCallThisMethod() {
        try {
            dontCallThisMethod();
        } catch (StackOverflowError e) {
            System.out.println("Caught StackOverflowError: " + e.getMessage());
        }
    }

//    public static void main(String[] args) {

//        TreeMap<String, String> tree = new TreeMap<>();
//        tree.put("see1", "bus1");
//        tree.put("see2", "bus2");
//        tree.put("see3", "bus3");
//        System.out.println(tree.ceilingEntry("see1"));
//        System.out.println(tree.get("see1"));
//        System.out.println(reverse("Bicycle"));
//        System.out.println(reverse("Home"));
//        System.out.println(reverse("Cat"));
//        System.out.println(reverse2("Hello, world!"));
//        System.out.println(reverse2("Hello"));
//        System.out.println(reverse3("Hello"));
//        System.out.println(reverse3("Hello"));
//        System.out.println(filterEvenNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
//        System.out.println(maxNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
//    }

    private static String reverse(String string) {
        int length = string.length();
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = string.charAt(length - i - 1);
        }
        return new String(result);
    }

    private static String reverse2(String string) {
        String result = "";
        for (int i = string.length() - 1; i > 0; i--) {
            result += string.charAt(i);
        }
        return result;
    }

    private static String reverse3(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = string.length() - 1; i > 0; i--) {
            result.append(string.charAt(i));
        }
        return result.toString();
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
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("Target number cannot be achieved by the sum of provided numbers in the array");


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

    public int getDuplicateUsingStreamApi(List<Integer> numbers) {
        Map<Integer, Integer> numberCounts = new HashMap<>();
        return numbers.stream()
                .filter(n -> {
                    int count = numberCounts.getOrDefault(n, 0);
                    numberCounts.put(n, count + 1);
                    return count > 0;
                }).findFirst().orElse(0);


//        return numbers.stream()
//                .filter(number -> {
//                    int count = numberCounts.getOrDefault(number, 0);
//                    numberCounts.put(number, count + 1);
//                    return count > 0;
//                }).findFirst().orElse(0); // Assuming 0 is returned if no duplicate is found
    }

    public boolean isPalindrome(String original) {
        StringBuilder sb = new StringBuilder(original.toLowerCase());
        String reversed = sb.reverse().toString();
//        for (int i = 0; i < original.length(); i++){
//            if (original.charAt(i) == reversed.charAt(i)){
//                continue;
//            } else {
//                return false;
//            }
//        }
        return reversed.equals(original);
//        return true;
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

//        StringBuilder result = new StringBuilder();
//        char previousChar = str.charAt(0);
//        result.append(previousChar);
//
//        for (int i = 1; i < str.length(); i++) {
//
//            char currentChar = str.charAt(i);
//            if (currentChar != previousChar) {
//                result.append(currentChar);
//                previousChar = currentChar;
//            }
//        }
//        return result.toString();
    }

    /**
     * Returns a sorted array using bubble sort algorithm
     *
     * @param array to sort
     * @return a sorted array
     */
    public int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
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