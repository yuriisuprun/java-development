package com.suprun.comparablecomparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Comparable and Comparator Operations Tests")
class ComparableComparatorOperationsTest {

    private ComparableComparatorOperations operations;
    private List<Person> people;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        operations = new ComparableComparatorOperations();

        people = Arrays.asList(
                new Person("Alice", 30, 75000),
                new Person("Bob", 25, 65000),
                new Person("Charlie", 35, 85000),
                new Person("David", 28, 70000)
        );

        products = Arrays.asList(
                new Product("Laptop", 1200.0, 5),
                new Product("Mouse", 25.0, 50),
                new Product("Keyboard", 75.0, 30),
                new Product("Monitor", 300.0, 10)
        );
    }

    @Test
    @DisplayName("Sort natural order with Comparable")
    void testSortNatural() {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        List<Integer> sorted = operations.sortNatural(numbers);

        assertEquals(Arrays.asList(1, 2, 3, 5, 8, 9), sorted);
    }

    @Test
    @DisplayName("Sort reverse order with Comparable")
    void testSortNaturalReverse() {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        List<Integer> sorted = operations.sortNaturalReverse(numbers);

        assertEquals(Arrays.asList(9, 8, 5, 3, 2, 1), sorted);
    }

    @Test
    @DisplayName("Sort people by age")
    void testSortPeopleByAge() {
        List<Person> sorted = operations.sortPeopleByAge(people);

        assertEquals("Bob", sorted.get(0).getName());
        assertEquals("David", sorted.get(1).getName());
        assertEquals("Alice", sorted.get(2).getName());
        assertEquals("Charlie", sorted.get(3).getName());
    }

    @Test
    @DisplayName("Sort people by age descending")
    void testSortPeopleByAgeDesc() {
        List<Person> sorted = operations.sortPeopleByAgeDesc(people);

        assertEquals("Charlie", sorted.get(0).getName());
        assertEquals("Alice", sorted.get(1).getName());
        assertEquals("David", sorted.get(2).getName());
        assertEquals("Bob", sorted.get(3).getName());
    }

    @Test
    @DisplayName("Sort people by name")
    void testSortPeopleByName() {
        List<Person> sorted = operations.sortPeopleByName(people);

        assertEquals("Alice", sorted.get(0).getName());
        assertEquals("Bob", sorted.get(1).getName());
        assertEquals("Charlie", sorted.get(2).getName());
        assertEquals("David", sorted.get(3).getName());
    }

    @Test
    @DisplayName("Sort people by salary descending")
    void testSortPeopleBySalaryDesc() {
        List<Person> sorted = operations.sortPeopleBySalaryDesc(people);

        assertEquals("Charlie", sorted.get(0).getName()); // 85000
        assertEquals("Alice", sorted.get(1).getName());   // 75000
        assertEquals("David", sorted.get(2).getName());   // 70000
        assertEquals("Bob", sorted.get(3).getName());     // 65000
    }

    @Test
    @DisplayName("Sort people by multiple criteria")
    void testSortPeopleMultipleCriteria() {
        List<Person> testPeople = Arrays.asList(
                new Person("Alice", 30, 75000),
                new Person("Bob", 30, 65000),
                new Person("Charlie", 25, 85000)
        );

        List<Person> sorted = operations.sortPeopleMultipleCriteria(testPeople);

        assertEquals("Charlie", sorted.get(0).getName()); // age 25
        assertEquals("Alice", sorted.get(1).getName());   // age 30, name Alice (comes before Bob alphabetically)
        assertEquals("Bob", sorted.get(2).getName());     // age 30, name Bob
    }

    @Test
    @DisplayName("Sort people by salary then age")
    void testSortPeopleBySalaryThenAge() {
        List<Person> testPeople = Arrays.asList(
                new Person("Alice", 30, 75000),
                new Person("Bob", 25, 75000),
                new Person("Charlie", 35, 85000)
        );

        List<Person> sorted = operations.sortPeopleBySalaryThenAge(testPeople);

        assertEquals("Charlie", sorted.get(0).getName());   // salary 85000
        assertEquals("Bob", sorted.get(1).getName());       // salary 75000, age 25
        assertEquals("Alice", sorted.get(2).getName());     // salary 75000, age 30
    }

    @Test
    @DisplayName("Sort products by price")
    void testSortProductsByPrice() {
        List<Product> sorted = operations.sortProductsByPrice(products);

        assertEquals("Mouse", sorted.get(0).getName());     // 25.0
        assertEquals("Keyboard", sorted.get(1).getName());  // 75.0
        assertEquals("Monitor", sorted.get(2).getName());   // 300.0
        assertEquals("Laptop", sorted.get(3).getName());    // 1200.0
    }

    @Test
    @DisplayName("Sort products by name")
    void testSortProductsByName() {
        List<Product> sorted = operations.sortProductsByName(products);

        assertEquals("Keyboard", sorted.get(0).getName());
        assertEquals("Laptop", sorted.get(1).getName());
        assertEquals("Monitor", sorted.get(2).getName());
        assertEquals("Mouse", sorted.get(3).getName());
    }

    @Test
    @DisplayName("Sort products by quantity descending")
    void testSortProductsByQuantityDesc() {
        List<Product> sorted = operations.sortProductsByQuantityDesc(products);

        assertEquals("Mouse", sorted.get(0).getName());     // 50
        assertEquals("Keyboard", sorted.get(1).getName());  // 30
        assertEquals("Monitor", sorted.get(2).getName());   // 10
        assertEquals("Laptop", sorted.get(3).getName());    // 5
    }

    @Test
    @DisplayName("Sort products by multiple criteria")
    void testSortProductsMultipleCriteria() {
        List<Product> sorted = operations.sortProductsMultipleCriteria(products);

        assertEquals("Laptop", sorted.get(0).getName());    // price 1200.0
        assertEquals("Monitor", sorted.get(1).getName());   // price 300.0
        assertEquals("Keyboard", sorted.get(2).getName());  // price 75.0
        assertEquals("Mouse", sorted.get(3).getName());     // price 25.0
    }

    @Test
    @DisplayName("Sort strings case-insensitive")
    void testSortStringsCaseInsensitive() {
        List<String> strings = Arrays.asList("zebra", "Apple", "banana", "MANGO");
        List<String> sorted = operations.sortStringsCaseInsensitive(strings);

        assertEquals("Apple", sorted.get(0));
        assertEquals("banana", sorted.get(1));
        assertEquals("MANGO", sorted.get(2));
        assertEquals("zebra", sorted.get(3));
    }

    @Test
    @DisplayName("Find max element")
    void testFindMax() {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        Integer max = operations.findMax(numbers);

        assertEquals(9, max);
    }

    @Test
    @DisplayName("Find min element")
    void testFindMin() {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        Integer min = operations.findMin(numbers);

        assertEquals(1, min);
    }

    @Test
    @DisplayName("Find oldest person")
    void testFindOldestPerson() {
        Person oldest = operations.findOldestPerson(people);

        assertEquals("Charlie", oldest.getName());
        assertEquals(35, oldest.getAge());
    }

    @Test
    @DisplayName("Find youngest person")
    void testFindYoungestPerson() {
        Person youngest = operations.findYoungestPerson(people);

        assertEquals("Bob", youngest.getName());
        assertEquals(25, youngest.getAge());
    }

    @Test
    @DisplayName("Find most expensive product")
    void testFindMostExpensiveProduct() {
        Product expensive = operations.findMostExpensiveProduct(products);

        assertEquals("Laptop", expensive.getName());
        assertEquals(1200.0, expensive.getPrice());
    }

    @Test
    @DisplayName("Find cheapest product")
    void testFindCheapestProduct() {
        Product cheapest = operations.findCheapestProduct(products);

        assertEquals("Mouse", cheapest.getName());
        assertEquals(25.0, cheapest.getPrice());
    }

    @Test
    @DisplayName("Sort in-place with natural order")
    void testSortInPlace() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        operations.sortInPlace(numbers);

        assertEquals(Arrays.asList(1, 2, 3, 5, 8, 9), numbers);
    }

    @Test
    @DisplayName("Sort in-place with custom comparator")
    void testSortInPlaceWithComparator() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        operations.sortInPlaceWithComparator(numbers, (a, b) -> b.compareTo(a)); // reverse

        assertEquals(Arrays.asList(9, 8, 5, 3, 2, 1), numbers);
    }

    @Test
    @DisplayName("Check if list is sorted")
    void testIsSorted() {
        List<Integer> sorted = Arrays.asList(1, 2, 3, 5, 8, 9);
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9, 3);

        assertTrue(operations.isSorted(sorted));
        assertFalse(operations.isSorted(unsorted));
    }

    @Test
    @DisplayName("Person compareTo with same age")
    void testPersonComparableEqual() {
        Person p1 = new Person("Alice", 30, 75000);
        Person p2 = new Person("Bob", 30, 65000);

        assertEquals(0, p1.compareTo(p2));
    }

    @Test
    @DisplayName("Person compareTo with different ages")
    void testPersonComparableLess() {
        Person younger = new Person("Alice", 25, 75000);
        Person older = new Person("Bob", 30, 65000);

        assertTrue(younger.compareTo(older) < 0);
        assertTrue(older.compareTo(younger) > 0);
    }

    @Test
    @DisplayName("Product compareTo with different prices")
    void testProductComparable() {
        Product cheap = new Product("Mouse", 25.0, 50);
        Product expensive = new Product("Laptop", 1200.0, 5);

        assertTrue(cheap.compareTo(expensive) < 0);
        assertTrue(expensive.compareTo(cheap) > 0);
    }

    @Test
    @DisplayName("Comparator null handling in Person")
    void testPersonComparableNullHandling() {
        Person person = new Person("Alice", 30, 75000);

        assertThrows(NullPointerException.class, () -> person.compareTo(null));
    }

    @Test
    @DisplayName("Find max on empty list")
    void testFindMaxEmptyList() {
        List<Integer> empty = new ArrayList<>();
        Integer max = operations.findMax(empty);

        assertNull(max);
    }

    @Test
    @DisplayName("Find min on empty list")
    void testFindMinEmptyList() {
        List<Integer> empty = new ArrayList<>();
        Integer min = operations.findMin(empty);

        assertNull(min);
    }
}
