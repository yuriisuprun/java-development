package com.suprun.streamapi;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

/**
 * @author Yurii_Suprun
 */
@AllArgsConstructor
public class StreamApi {

    /**
     * Returns a {@link List} of strings with sorted strings by frequency
     *
     * @param textWithHashtags a text with hashtags
     * @return list of strings
     */
    public List<String> getSortedHashCodes(String textWithHashtags) {
        return Arrays.stream(textWithHashtags.split(" "))
                .filter(word -> word.startsWith("#"))
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Returns a length of the longest string in provided list
     *
     * @param list a list of strings
     * @return a string length
     */
    public int getLongestStringLength(List<String> list) {
        OptionalInt response = list.stream()
                .mapToInt(String::length)
                .max();
        if (response.isPresent()) {
            return response.getAsInt();
        } else {
            return 0;
        }
    }


    /**
     * Returns a sorted {@link List} of strings without first letters
     *
     * @param list a list of strings
     * @return a list of strings
     */
    public List<String> deleteFirstLetterFromStrings(List<String> list) {
        return list.stream()
                .map(string -> string.substring(1))
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    /**
     * Returns a letters sum of string which length is more than @param wordLengthToCount
     *
     * @param list              a list of strings
     * @param wordLengthToCount a list of strings
     * @return a string length
     */
    public int countLettersForStringsLongerThan(List<String> list, int wordLengthToCount) {
        return list.stream()
                .map(String::length)
                .filter(length -> length > wordLengthToCount)
                .reduce(0, Integer::sum);

//        return originalList.stream()
//                .mapToInt(String::length)
//                .filter(length -> length > wordLengthToCount)
//                .sum();
    }

    /**
     * Returns the oldest Person
     *
     * @param people a list of People objects
     * @return a Person object
     */
    public Person findOldestPerson(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getAge, Comparator.reverseOrder()))
                .findFirst().get();
    }

    /**
     * Returns a map where key is true if a person is older than @param age
     *
     * @param people a list of People objects
     * @param age    an age of people to divide on 2 groups
     * @return a Map with Boolean and List of Person as key and value
     */
    public Map<Boolean, List<Person>> partitionPeopleByAge(List<Person> people, int age) {
        return people.stream()
                .collect(partitioningBy(person -> person.getAge() > age));
    }

    /**
     * Returns a map where key is a character and value is the frequency of the letter in the word
     *
     * @param word an incoming word to count the letters in it
     * @return a Map with Character and Long as key and value
     */
    public Map<Character, Long> countLettersInWord(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(groupingBy(Function.identity(), counting()));
    }

    /**
     * Returns a map where key is a character and value is the frequency of the letter in the word
     *
     * @param word an incoming word to count the letters in it
     * @return a Map with Character and Long as key and value
     */
    public Map<Character, Long> countLettersInWordOldStyle(String word) {
        char[] chars = word.toCharArray();
        Map<Character, Long> charactersByFrequency = new HashMap<>();
        for (char c : chars) {
            if (!charactersByFrequency.containsKey(c)) {
                charactersByFrequency.put(c, 0L);
            }
            long frequency = charactersByFrequency.get(c);
            frequency++;
            charactersByFrequency.put(c, frequency);
        }
        return charactersByFrequency;
    }

    public void printParallelStreamListWithOrder() {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        list.parallelStream().forEachOrdered(System.out::println);
    }

    List<Employee> employees = Arrays.asList(
            new Employee(8, "Robert", "manager", 590L),
            new Employee(2, "Rob", "manager", 790L),
            new Employee(3, "Joshua", "dev", 1200L),
            new Employee(5, "Bill", "hr", 2300L)
    );

    public void printEmployees() {
        System.out.println(employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getSalary() % 1000)));
    }
}