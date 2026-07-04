package com.suprun.comparablecomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates Comparable and Comparator usage in Java 8.
 * Includes natural ordering and custom sorting strategies.
 */
public class ComparableComparatorOperations {

    /**
     * Sort a list using natural order (Comparable).
     * Works with any Comparable object.
     *
     * @param list the list to sort
     * @param <T>  the type of elements, must implement Comparable
     * @return a new sorted list
     */
    public <T extends Comparable<T>> List<T> sortNatural(List<T> list) {
        return list.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Sort a list in reverse order using natural order.
     *
     * @param list the list to sort
     * @param <T>  the type of elements, must implement Comparable
     * @return a new reverse sorted list
     */
    public <T extends Comparable<T>> List<T> sortNaturalReverse(List<T> list) {
        return list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /**
     * Sort people by age (using Comparable's natural order).
     *
     * @param people the list of people to sort
     * @return sorted list by age ascending
     */
    public List<Person> sortPeopleByAge(List<Person> people) {
        return people.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Sort people by age in descending order.
     *
     * @param people the list of people to sort
     * @return sorted list by age descending
     */
    public List<Person> sortPeopleByAgeDesc(List<Person> people) {
        return people.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /**
     * Sort people by name using Comparator.
     *
     * @param people the list of people to sort
     * @return sorted list by name
     */
    public List<Person> sortPeopleByName(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }

    /**
     * Sort people by salary in descending order.
     *
     * @param people the list of people to sort
     * @return sorted list by salary descending
     */
    public List<Person> sortPeopleBySalaryDesc(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparingDouble(Person::getSalary).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Sort people using multiple criteria: first by age, then by name.
     *
     * @param people the list of people to sort
     * @return sorted list by age, then name
     */
    public List<Person> sortPeopleMultipleCriteria(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparingInt(Person::getAge)
                        .thenComparing(Person::getName))
                .collect(Collectors.toList());
    }

    /**
     * Sort people: salary descending, then age ascending.
     *
     * @param people the list of people to sort
     * @return sorted list by salary desc, then age asc
     */
    public List<Person> sortPeopleBySalaryThenAge(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparingDouble(Person::getSalary).reversed()
                        .thenComparingInt(Person::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Sort products by price (using Comparable's natural order).
     *
     * @param products the list of products to sort
     * @return sorted list by price ascending
     */
    public List<Product> sortProductsByPrice(List<Product> products) {
        return products.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Sort products by name using Comparator.
     *
     * @param products the list of products to sort
     * @return sorted list by name
     */
    public List<Product> sortProductsByName(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    /**
     * Sort products by quantity in descending order.
     *
     * @param products the list of products to sort
     * @return sorted list by quantity descending
     */
    public List<Product> sortProductsByQuantityDesc(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparingInt(Product::getQuantity).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Sort products: price descending, then quantity ascending.
     *
     * @param products the list of products to sort
     * @return sorted list by price desc, then quantity asc
     */
    public List<Product> sortProductsMultipleCriteria(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed()
                        .thenComparingInt(Product::getQuantity))
                .collect(Collectors.toList());
    }

    /**
     * Create custom comparator for case-insensitive string comparison.
     *
     * @return a comparator for case-insensitive string sorting
     */
    public Comparator<String> caseInsensitiveComparator() {
        return String::compareToIgnoreCase;
    }

    /**
     * Sort strings using case-insensitive comparison.
     *
     * @param strings the list of strings to sort
     * @return sorted list case-insensitively
     */
    public List<String> sortStringsCaseInsensitive(List<String> strings) {
        return strings.stream()
                .sorted(caseInsensitiveComparator())
                .collect(Collectors.toList());
    }

    /**
     * Find the maximum element in a list using natural order.
     *
     * @param list the list to search
     * @param <T>  the type of elements, must implement Comparable
     * @return the maximum element, or null if list is empty
     */
    public <T extends Comparable<T>> T findMax(List<T> list) {
        return list.stream()
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    /**
     * Find the minimum element in a list using natural order.
     *
     * @param list the list to search
     * @param <T>  the type of elements, must implement Comparable
     * @return the minimum element, or null if list is empty
     */
    public <T extends Comparable<T>> T findMin(List<T> list) {
        return list.stream()
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

    /**
     * Find the oldest person in a list.
     *
     * @param people the list of people
     * @return the oldest person, or null if list is empty
     */
    public Person findOldestPerson(List<Person> people) {
        return people.stream()
                .max(Comparator.comparingInt(Person::getAge))
                .orElse(null);
    }

    /**
     * Find the youngest person in a list.
     *
     * @param people the list of people
     * @return the youngest person, or null if list is empty
     */
    public Person findYoungestPerson(List<Person> people) {
        return people.stream()
                .min(Comparator.comparingInt(Person::getAge))
                .orElse(null);
    }

    /**
     * Find the most expensive product.
     *
     * @param products the list of products
     * @return the most expensive product, or null if list is empty
     */
    public Product findMostExpensiveProduct(List<Product> products) {
        return products.stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

    /**
     * Find the cheapest product.
     *
     * @param products the list of products
     * @return the cheapest product, or null if list is empty
     */
    public Product findCheapestProduct(List<Product> products) {
        return products.stream()
                .min(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

    /**
     * Sort in-place using Collections.sort (for Comparable objects).
     *
     * @param list the list to sort in-place
     * @param <T>  the type of elements, must implement Comparable
     */
    public <T extends Comparable<T>> void sortInPlace(List<T> list) {
        Collections.sort(list);
    }

    /**
     * Sort in-place using a custom comparator.
     *
     * @param list       the list to sort in-place
     * @param comparator the comparator to use
     * @param <T>        the type of elements
     */
    public <T> void sortInPlaceWithComparator(List<T> list, Comparator<T> comparator) {
        Collections.sort(list, comparator);
    }

    /**
     * Compare two objects using a comparator.
     *
     * @param obj1      the first object
     * @param obj2      the second object
     * @param comparator the comparator to use
     * @param <T>       the type of objects
     * @return negative if obj1 < obj2, 0 if equal, positive if obj1 > obj2
     */
    public <T> int compare(T obj1, T obj2, Comparator<T> comparator) {
        return comparator.compare(obj1, obj2);
    }

    /**
     * Check if a list is sorted according to natural order.
     *
     * @param list the list to check
     * @param <T>  the type of elements, must implement Comparable
     * @return true if sorted, false otherwise
     */
    public <T extends Comparable<T>> boolean isSorted(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
