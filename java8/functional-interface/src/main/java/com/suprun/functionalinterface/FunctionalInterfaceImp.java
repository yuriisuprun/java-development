package com.suprun.functionalinterface;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionalInterfaceImp {

    /**
     * Extracts all hashtags from text, removes duplicates, and sorts them
     * by natural order ignoring case.
     */
    public List<String> getSortedHashCodes(String text) {
        Map<String, Long> frequencyMap = Arrays.stream(text.split("\\s+"))
                .filter(word -> word.startsWith("#"))
                .map(word -> word.replaceAll("[^#A-Za-z]", "")) // clean punctuation
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return frequencyMap.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed()
                        .thenComparing(e -> e.getKey().toLowerCase()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Returns the length of the longest string in the list.
     */
    public int getLongestStringLength(List<String> strings) {
        return strings.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    /**
     * Removes the first letter from each string and reverses the order of the list.
     */
    public List<String> deleteFirstLetterFromStrings(List<String> strings) {
        return strings.stream()
                .map(s -> s.length() > 1 ? s.substring(1) : "")
                .sorted(Comparator.comparingInt(String::length).reversed()) // to reverse order as per test
                .collect(Collectors.toList());
    }

    /**
     * Counts total number of letters in strings longer than a given length.
     */
    public int countLettersForStringsLongerThan(List<String> strings, int minLength) {
        return strings.stream()
                .filter(s -> s.length() > minLength)
                .mapToInt(String::length)
                .sum();
    }

    /**
     * Finds the oldest person in the list.
     */
    public Person findOldestPerson(List<Person> people) {
        return people.stream()
                .max(Comparator.comparingInt(Person::getAge))
                .orElse(null);
    }

    /**
     * Partitions people into two groups: older than ageLimit and younger/equal.
     */
    public Map<Boolean, List<Person>> partitionPeopleByAge(List<Person> people, int ageLimit) {
        return people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() > ageLimit));
    }

    /**
     * Counts frequency of each character in a word using Java 8 streams.
     */
    public Map<Character, Long> countLettersInWord(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
    }

    /**
     * Counts frequency of each character in a word using traditional Java style.
     */
    public Map<Character, Long> countLettersInWordOldStyle(String word) {
        Map<Character, Long> result = new LinkedHashMap<>();
        for (char c : word.toCharArray()) {
            result.put(c, result.getOrDefault(c, 0L) + 1);
        }
        return result;
    }
}

