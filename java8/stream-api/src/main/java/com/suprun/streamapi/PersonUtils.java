package com.suprun.streamapi;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility class for Person stream operations.
 * Demonstrates domain-specific stream patterns using generic CollectionUtils.
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
     * Delegates to generic CollectionUtils.findMax().
     * 
     * @param people a collection of persons
     * @return the oldest person
     * @throws IllegalArgumentException if collection is empty
     */
    public static Person findOldest(Collection<Person> people) {
        return CollectionUtils.findMax(people, Comparator.comparingInt(Person::getAge));
    }

    /**
     * Finds the youngest person in a collection.
     * Delegates to generic CollectionUtils.findMin().
     * 
     * @param people a collection of persons
     * @return the youngest person
     * @throws IllegalArgumentException if collection is empty
     */
    public static Person findYoungest(Collection<Person> people) {
        return CollectionUtils.findMin(people, Comparator.comparingInt(Person::getAge));
    }

    /**
     * Partitions people by age threshold.
     * Delegates to generic CollectionUtils.partition().
     * 
     * @param people a collection of persons
     * @param ageThreshold the age threshold
     * @return map with Boolean (older/younger) as key and person list as value
     */
    public static Map<Boolean, List<Person>> partitionByAge(Collection<Person> people, int ageThreshold) {
        return CollectionUtils.partition(people, p -> p.getAge() > ageThreshold);
    }

    /**
     * Groups people by age.
     * Delegates to generic CollectionUtils.groupBy().
     * 
     * @param people a collection of persons
     * @return map with age as key and person list as value
     */
    public static Map<Integer, List<Person>> groupByAge(Collection<Person> people) {
        return CollectionUtils.groupBy(people, Person::getAge);
    }

    /**
     * Groups people by nationality.
     * Delegates to generic CollectionUtils.groupBy().
     * 
     * @param people a collection of persons
     * @return map with nationality as key and person list as value
     */
    public static Map<String, List<Person>> groupByNationality(Collection<Person> people) {
        return CollectionUtils.groupBy(people, Person::getNationality);
    }

    /**
     * Sorts people by age in ascending order.
     * Delegates to generic CollectionUtils.sortAscending().
     * 
     * @param people a collection of persons
     * @return sorted list by age
     */
    public static List<Person> sortByAgeAscending(Collection<Person> people) {
        return CollectionUtils.sortAscending(people, Comparator.comparingInt(Person::getAge));
    }

    /**
     * Sorts people by age in descending order.
     * Delegates to generic CollectionUtils.sortDescending().
     * 
     * @param people a collection of persons
     * @return sorted list by age descending
     */
    public static List<Person> sortByAgeDescending(Collection<Person> people) {
        return CollectionUtils.sortDescending(people, Comparator.comparingInt(Person::getAge));
    }

    /**
     * Sorts people by name.
     * Delegates to generic CollectionUtils.sortAscending().
     * 
     * @param people a collection of persons
     * @return sorted list by name
     */
    public static List<Person> sortByName(Collection<Person> people) {
        return CollectionUtils.sortAscending(people, Comparator.comparing(Person::getName));
    }

    /**
     * Filters people by minimum age.
     * Delegates to generic CollectionUtils.filterByMinValue().
     * 
     * @param people a collection of persons
     * @param minAge the minimum age threshold
     * @return list of people aged minAge or older
     */
    public static List<Person> filterByMinAge(Collection<Person> people, int minAge) {
        return CollectionUtils.filterByMinValue(people, Person::getAge, minAge);
    }

    /**
     * Filters people by maximum age.
     * Delegates to generic CollectionUtils.filterByMaxValue().
     * 
     * @param people a collection of persons
     * @param maxAge the maximum age threshold
     * @return list of people aged maxAge or younger
     */
    public static List<Person> filterByMaxAge(Collection<Person> people, int maxAge) {
        return CollectionUtils.filterByMaxValue(people, Person::getAge, maxAge);
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
     * Optimized: uses single stream pass with Person.isAdult() predicate.
     * 
     * @param people a collection of persons
     * @return count of adults
     */
    public static long countAdults(Collection<Person> people) {
        return CollectionUtils.count(people, Person::isAdult);
    }

    /**
     * Counts minors (age < 18) in the collection.
     * Optimized: uses single stream pass with Person.isMinor() predicate.
     * 
     * @param people a collection of persons
     * @return count of minors
     */
    public static long countMinors(Collection<Person> people) {
        return CollectionUtils.count(people, Person::isMinor);
    }

    /**
     * Extracts names from people collection.
     * Delegates to generic CollectionUtils.map().
     * 
     * @param people a collection of persons
     * @return list of names
     */
    public static List<String> extractNames(Collection<Person> people) {
        return CollectionUtils.map(people, Person::getName);
    }
}
