package com.suprun.streamapi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

/**
 * Comprehensive Stream API demonstrations and utilities.
 * 
 * <p>This class provides various stream operations for strings, people, and employees,
 * demonstrating common patterns and best practices with Java 8+ streams.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public class StreamApi {

    /**
     * Extracts hashtags from text and returns them sorted by frequency (descending).
     * 
     * @param textWithHashtags a text containing hashtags
     * @return list of unique hashtags sorted by frequency
     */
    public List<String> getSortedHashCodes(String textWithHashtags) {
        Objects.requireNonNull(textWithHashtags, "textWithHashtags cannot be null");
        return Arrays.stream(textWithHashtags.split(" "))
                     .filter(word -> word.startsWith("#"))
                     .collect(groupingBy(Function.identity(), counting()))
                     .entrySet().stream()
                     .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                     .map(Map.Entry::getKey)
                     .collect(Collectors.toList());
    }

    /**
     * Returns the length of the longest string in a list.
     * 
     * @param list a list of strings
     * @return the length of the longest string, or 0 if empty
     */
    public int getLongestStringLength(List<String> list) {
        return StreamUtils.getLongestStringLength(list);
    }

    /**
     * Returns a list of strings with the first character removed, sorted in reverse order.
     * 
     * @param list a list of strings
     * @return transformed list sorted reverse
     */
    public List<String> deleteFirstLetterFromStrings(List<String> list) {
        Objects.requireNonNull(list, "list cannot be null");
        return list.stream()
                   .filter(s -> !s.isEmpty())
                   .map(string -> string.substring(1))
                   .sorted(Comparator.reverseOrder())
                   .collect(Collectors.toList());
    }

    /**
     * Counts the total letters in strings longer than a specified length.
     * 
     * @param list a list of strings
     * @param wordLengthToCount the minimum length threshold
     * @return total letter count of strings exceeding threshold
     */
    public int countLettersForStringsLongerThan(List<String> list, int wordLengthToCount) {
        Objects.requireNonNull(list, "list cannot be null");
        return list.stream()
                   .mapToInt(String::length)
                   .filter(length -> length > wordLengthToCount)
                   .sum();
    }

    /**
     * Finds the oldest person in a list.
     * 
     * @param people a list of people
     * @return the oldest person
     * @throws IllegalArgumentException if list is empty
     */
    public Person findOldestPerson(List<Person> people) {
        return PersonUtils.findOldest(people);
    }

    /**
     * Finds the youngest person in a list.
     * 
     * @param people a list of people
     * @return the youngest person
     * @throws IllegalArgumentException if list is empty
     */
    public Person findYoungestPerson(List<Person> people) {
        return PersonUtils.findYoungest(people);
    }

    /**
     * Partitions people by age threshold.
     * 
     * @param people a list of people
     * @param age the age threshold
     * @return map with true/false keys indicating older/younger
     */
    public Map<Boolean, List<Person>> partitionPeopleByAge(List<Person> people, int age) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .collect(partitioningBy(person -> person.getAge() > age));
    }

    /**
     * Counts character frequencies in a word.
     * 
     * @param word the word to analyze
     * @return map with character and frequency
     */
    public Map<Character, Long> countLettersInWord(String word) {
        Objects.requireNonNull(word, "word cannot be null");
        return word.chars()
                   .mapToObj(c -> (char) c)
                   .collect(groupingBy(Function.identity(), counting()));
    }

    /**
     * Counts character frequencies in a word using traditional iteration.
     * 
     * @param word the word to analyze
     * @return map with character and frequency
     */
    public Map<Character, Long> countLettersInWordOldStyle(String word) {
        Objects.requireNonNull(word, "word cannot be null");
        Map<Character, Long> charactersByFrequency = new java.util.HashMap<>();
        for (char c : word.toCharArray()) {
            charactersByFrequency.put(c, charactersByFrequency.getOrDefault(c, 0L) + 1L);
        }
        return charactersByFrequency;
    }

    /**
     * Demonstrates parallel stream with ordered output.
     */
    public void printParallelStreamListWithOrder() {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        list.parallelStream().forEachOrdered(System.out::println);
    }

    /**
     * Groups employees by salary remainder (for categorization).
     * 
     * @return map with remainder and employee list
     */
    public Map<Long, List<Employee>> groupEmployeesBySalaryRemainder() {
        List<Employee> employees = Arrays.asList(
                new Employee(8, "Robert", "manager", 590L),
                new Employee(2, "Rob", "manager", 790L),
                new Employee(3, "Joshua", "dev", 1200L),
                new Employee(5, "Bill", "hr", 2300L)
        );
        return EmployeeUtils.groupBySalaryRemainder(employees, 1000);
    }

    /**
     * Prints grouped employees by salary remainder.
     */
    public void printEmployees() {
        System.out.println(groupEmployeesBySalaryRemainder());
    }
}
