package com.suprun.streamapi;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility class for Person stream operations.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public final class PersonUtils {

    private PersonUtils() {
        // Prevent instantiation
    }

    /**
     * Finds the oldest person in a collection.
     * 
     * @param people a collection of persons
     * @return the oldest person
     * @throws IllegalArgumentException if collection is empty
     */
    public static Person findOldest(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .max(Comparator.comparingInt(Person::getAge))
                     .orElseThrow(() -> new IllegalArgumentException("people collection cannot be empty"));
    }

    /**
     * Finds the youngest person in a collection.
     * 
     * @param people a collection of persons
     * @return the youngest person
     * @throws IllegalArgumentException if collection is empty
     */
    public static Person findYoungest(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .min(Comparator.comparingInt(Person::getAge))
                     .orElseThrow(() -> new IllegalArgumentException("people collection cannot be empty"));
    }

    /**
     * Partitions people by age threshold.
     * 
     * @param people a collection of persons
     * @param ageThreshold the age threshold
     * @return map with Boolean (older/younger) as key and person list as value
     */
    public static Map<Boolean, List<Person>> partitionByAge(Collection<Person> people, int ageThreshold) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .collect(Collectors.partitioningBy(p -> p.getAge() > ageThreshold));
    }

    /**
     * Groups people by age.
     * 
     * @param people a collection of persons
     * @return map with age as key and person list as value
     */
    public static Map<Integer, List<Person>> groupByAge(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .collect(Collectors.groupingBy(Person::getAge));
    }

    /**
     * Groups people by nationality.
     * 
     * @param people a collection of persons
     * @return map with nationality as key and person list as value
     */
    public static Map<String, List<Person>> groupByNationality(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .collect(Collectors.groupingBy(Person::getNationality));
    }

    /**
     * Sorts people by age in ascending order.
     * 
     * @param people a collection of persons
     * @return sorted list by age
     */
    public static List<Person> sortByAgeAscending(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .sorted(Comparator.comparingInt(Person::getAge))
                     .collect(Collectors.toList());
    }

    /**
     * Sorts people by age in descending order.
     * 
     * @param people a collection of persons
     * @return sorted list by age descending
     */
    public static List<Person> sortByAgeDescending(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .sorted(Comparator.comparingInt(Person::getAge).reversed())
                     .collect(Collectors.toList());
    }

    /**
     * Sorts people by name.
     * 
     * @param people a collection of persons
     * @return sorted list by name
     */
    public static List<Person> sortByName(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .sorted(Comparator.comparing(Person::getName))
                     .collect(Collectors.toList());
    }

    /**
     * Filters people by minimum age.
     * 
     * @param people a collection of persons
     * @param minAge the minimum age threshold
     * @return list of people aged minAge or older
     */
    public static List<Person> filterByMinAge(Collection<Person> people, int minAge) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .filter(p -> p.getAge() >= minAge)
                     .collect(Collectors.toList());
    }

    /**
     * Filters people by maximum age.
     * 
     * @param people a collection of persons
     * @param maxAge the maximum age threshold
     * @return list of people aged maxAge or younger
     */
    public static List<Person> filterByMaxAge(Collection<Person> people, int maxAge) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .filter(p -> p.getAge() <= maxAge)
                     .collect(Collectors.toList());
    }

    /**
     * Filters people by nationality.
     * 
     * @param people a collection of persons
     * @param nationality the nationality to filter by
     * @return list of people with the specified nationality
     */
    public static List<Person> filterByNationality(Collection<Person> people, String nationality) {
        Objects.requireNonNull(people, "people cannot be null");
        Objects.requireNonNull(nationality, "nationality cannot be null");
        return people.stream()
                     .filter(p -> nationality.equals(p.getNationality()))
                     .collect(Collectors.toList());
    }

    /**
     * Calculates the average age of people.
     * 
     * @param people a collection of persons
     * @return average age, or 0 if empty
     */
    public static double getAverageAge(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .mapToInt(Person::getAge)
                     .average()
                     .orElse(0.0);
    }

    /**
     * Counts adults (age >= 18) in the collection.
     * 
     * @param people a collection of persons
     * @return count of adults
     */
    public static long countAdults(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .filter(p -> p.getAge() >= 18)
                     .count();
    }

    /**
     * Counts minors (age < 18) in the collection.
     * 
     * @param people a collection of persons
     * @return count of minors
     */
    public static long countMinors(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .filter(p -> p.getAge() < 18)
                     .count();
    }

    /**
     * Extracts names from people collection.
     * 
     * @param people a collection of persons
     * @return list of names
     */
    public static List<String> extractNames(Collection<Person> people) {
        Objects.requireNonNull(people, "people cannot be null");
        return people.stream()
                     .map(Person::getName)
                     .collect(Collectors.toList());
    }
}
