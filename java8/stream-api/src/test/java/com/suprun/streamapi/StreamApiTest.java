package com.suprun.streamapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Stream API module.
 * Tests StreamApi, StreamUtils, PersonUtils, and EmployeeUtils.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
@DisplayName("Stream API Tests")
public class StreamApiTest {

    private StreamApi streamApi;

    @BeforeEach
    void setUp() {
        streamApi = new StreamApi();
    }

    // ===== StreamApi Tests =====

    @Test
    @DisplayName("Get sorted hashtags by frequency")
    void testGetSortedHashStringsList() {
        List<String> expectedList = Arrays.asList(
                "#Workout", "#podcast", "#MentalHealth", "#SelfCare", "#personalGrowth", "#MeTime"
        );

        List<String> actualList = streamApi.getSortedHashCodes(hashTagText());

        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Get length of longest string")
    void testGetLengthOfLongestString() {
        List<String> list = Arrays.asList("air", "grass", "road", "buildings");

        int actualLength = streamApi.getLongestStringLength(list);

        assertEquals("buildings".length(), actualLength);
    }

    @Test
    @DisplayName("Delete first letter from strings")
    void testDeleteFirstLetterFromStrings() {
        List<String> originalList = Arrays.asList("air", "grass", "road", "buildings");
        List<String> expectedList = Arrays.asList("uildings", "rass", "oad", "ir");

        List<String> actualList = streamApi.deleteFirstLetterFromStrings(originalList);

        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Count letters for strings longer than threshold")
    void testCountLettersForStringsLongerThan() {
        List<String> originalList = Arrays.asList("Bill", "Tom", "Ronny", "Michelangelo", "Alexander");

        int actualSum = streamApi.countLettersForStringsLongerThan(originalList, 4);

        assertEquals(26, actualSum);
    }

    @Test
    @DisplayName("Find oldest person")
    void testFindOldestPerson() {
        List<Person> people = Arrays.asList(new Person("Sara", 4), new Person("Viktor", 40), new Person("Eva", 42));
        Person expectedPerson = new Person("Eva", 42);

        Person actualPerson = streamApi.findOldestPerson(people);

        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    @DisplayName("Partition people by age")
    void testPartitionPeopleByAge() {
        List<Person> people = Arrays.asList(
                new Person("Sara", 4),
                new Person("Chiara", 17),
                new Person("Viktor", 40),
                new Person("Eva", 42)
        );
        Map<Boolean, List<Person>> expectedMap = new HashMap<>();
        expectedMap.put(false, Arrays.asList(new Person("Sara", 4), new Person("Chiara", 17)));
        expectedMap.put(true, Arrays.asList(new Person("Viktor", 40), new Person("Eva", 42)));

        Map<Boolean, List<Person>> actualMap = streamApi.partitionPeopleByAge(people, 17);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    @DisplayName("Count letters in word with stream")
    void testCountLettersInWord() {
        Map<Character, Long> actualMap = streamApi.countLettersInWord("Discussion");

        assertEquals(expectedLetterFrequency(), actualMap);
    }

    @Test
    @DisplayName("Count letters in word old style")
    void testCountLettersInWordOldStyle() {
        Map<Character, Long> actualMap = streamApi.countLettersInWordOldStyle("Discussion");

        assertEquals(expectedLetterFrequency(), actualMap);
    }

    @Test
    @DisplayName("Group employees by salary remainder")
    void testGroupEmployeesBySalaryRemainder() {
        Map<Long, List<Employee>> groupedEmployees = streamApi.groupEmployeesBySalaryRemainder();

        assertEquals(4, groupedEmployees.size());
        assertEquals("Joshua", groupedEmployees.get(200L).get(0).getName());
        assertEquals("Bill", groupedEmployees.get(300L).get(0).getName());
        assertEquals("Robert", groupedEmployees.get(590L).get(0).getName());
        assertEquals("Rob", groupedEmployees.get(790L).get(0).getName());
    }

    // ===== StreamUtils Tests =====

    @Test
    @DisplayName("StreamUtils: Find longest string")
    void testStreamUtilsFindLongestString() {
        List<String> strings = Arrays.asList("a", "bb", "ccc", "dd");
        assertEquals("ccc", StreamUtils.findLongestString(strings));
    }

    @Test
    @DisplayName("StreamUtils: Find shortest string")
    void testStreamUtilsFindShortestString() {
        List<String> strings = Arrays.asList("apple", "b", "cat");
        assertEquals("b", StreamUtils.findShortestString(strings));
    }

    @Test
    @DisplayName("StreamUtils: Sum string lengths")
    void testStreamUtilsSumStringLengths() {
        List<String> strings = Arrays.asList("a", "bb", "ccc");
        assertEquals(6, StreamUtils.sumStringLengths(strings));
    }

    @Test
    @DisplayName("StreamUtils: Average string length")
    void testStreamUtilsAverageStringLength() {
        List<String> strings = Arrays.asList("a", "bb", "ccc");
        assertEquals(2.0, StreamUtils.averageStringLength(strings));
    }

    @Test
    @DisplayName("StreamUtils: Filter by min length")
    void testStreamUtilsFilterByMinLength() {
        List<String> strings = Arrays.asList("a", "bb", "ccc", "dd");
        List<String> result = StreamUtils.filterByMinLength(strings, 2);
        assertEquals(Arrays.asList("bb", "ccc", "dd"), result);
    }

    // ===== PersonUtils Tests =====

    @Test
    @DisplayName("PersonUtils: Find oldest")
    void testPersonUtilsFindOldest() {
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 35),
            new Person("Jack", 20)
        );
        Person oldest = PersonUtils.findOldest(people);
        assertEquals("Jane", oldest.getName());
        assertEquals(35, oldest.getAge());
    }

    @Test
    @DisplayName("PersonUtils: Find youngest")
    void testPersonUtilsFindYoungest() {
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 35),
            new Person("Jack", 20)
        );
        Person youngest = PersonUtils.findYoungest(people);
        assertEquals("Jack", youngest.getName());
        assertEquals(20, youngest.getAge());
    }

    @Test
    @DisplayName("PersonUtils: Sort by age ascending")
    void testPersonUtilsSortByAgeAscending() {
        List<Person> people = Arrays.asList(
            new Person("John", 30),
            new Person("Jane", 20),
            new Person("Jack", 40)
        );
        List<Person> sorted = PersonUtils.sortByAgeAscending(people);
        assertEquals(20, sorted.get(0).getAge());
        assertEquals(30, sorted.get(1).getAge());
        assertEquals(40, sorted.get(2).getAge());
    }

    @Test
    @DisplayName("PersonUtils: Count adults")
    void testPersonUtilsCountAdults() {
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 17),
            new Person("Jack", 30)
        );
        assertEquals(2, PersonUtils.countAdults(people));
    }

    @Test
    @DisplayName("PersonUtils: Average age")
    void testPersonUtilsAverageAge() {
        List<Person> people = Arrays.asList(
            new Person("John", 20),
            new Person("Jane", 30),
            new Person("Jack", 40)
        );
        assertEquals(30.0, PersonUtils.getAverageAge(people));
    }

    @Test
    @DisplayName("PersonUtils: Extract names")
    void testPersonUtilsExtractNames() {
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 30)
        );
        List<String> names = PersonUtils.extractNames(people);
        assertEquals(Arrays.asList("John", "Jane"), names);
    }

    // ===== EmployeeUtils Tests =====

    @Test
    @DisplayName("EmployeeUtils: Find highest paid")
    void testEmployeeUtilsFindHighestPaid() {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", "dev", 50000L),
            new Employee(2, "Jane", "manager", 80000L),
            new Employee(3, "Jack", "dev", 60000L)
        );
        Employee highest = EmployeeUtils.findHighestPaid(employees);
        assertEquals("Jane", highest.getName());
        assertEquals(80000L, highest.getSalary());
    }

    @Test
    @DisplayName("EmployeeUtils: Total salary")
    void testEmployeeUtilsTotalSalary() {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", "dev", 50000L),
            new Employee(2, "Jane", "manager", 80000L)
        );
        assertEquals(130000L, EmployeeUtils.getTotalSalary(employees));
    }

    @Test
    @DisplayName("EmployeeUtils: Average salary")
    void testEmployeeUtilsAverageSalary() {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", "dev", 50000L),
            new Employee(2, "Jane", "manager", 80000L),
            new Employee(3, "Jack", "dev", 60000L)
        );
        assertEquals(63333.33, EmployeeUtils.getAverageSalary(employees), 0.01);
    }

    @Test
    @DisplayName("EmployeeUtils: Group by position")
    void testEmployeeUtilsGroupByPosition() {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", "dev", 50000L),
            new Employee(2, "Jane", "manager", 80000L),
            new Employee(3, "Jack", "dev", 60000L)
        );
        Map<String, List<Employee>> grouped = EmployeeUtils.groupByPosition(employees);
        assertEquals(2, grouped.get("dev").size());
        assertEquals(1, grouped.get("manager").size());
    }

    @Test
    @DisplayName("EmployeeUtils: Sort by salary descending")
    void testEmployeeUtilsSortBySalaryDescending() {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", "dev", 50000L),
            new Employee(2, "Jane", "manager", 80000L),
            new Employee(3, "Jack", "dev", 60000L)
        );
        List<Employee> sorted = EmployeeUtils.sortBySalaryDescending(employees);
        assertEquals(80000L, sorted.get(0).getSalary());
        assertEquals(60000L, sorted.get(1).getSalary());
        assertEquals(50000L, sorted.get(2).getSalary());
    }

    @Test
    @DisplayName("EmployeeUtils: Filter by position")
    void testEmployeeUtilsFilterByPosition() {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", "dev", 50000L),
            new Employee(2, "Jane", "manager", 80000L),
            new Employee(3, "Jack", "dev", 60000L)
        );
        List<Employee> devs = EmployeeUtils.filterByPosition(employees, "dev");
        assertEquals(2, devs.size());
        assertTrue(devs.stream().allMatch(e -> "dev".equals(e.getPosition())));
    }

    @Test
    @DisplayName("Person: isAdult method")
    void testPersonIsAdult() {
        Person adult = new Person("John", 25);
        Person minor = new Person("Jane", 17);
        assertTrue(adult.isAdult());
        assertFalse(minor.isAdult());
    }

    @Test
    @DisplayName("Employee: isManager method")
    void testEmployeeIsManager() {
        Employee manager = new Employee(1, "John", "manager", 80000L);
        Employee dev = new Employee(2, "Jane", "dev", 60000L);
        assertTrue(manager.isManager());
        assertFalse(dev.isManager());
    }

    @Test
    @DisplayName("Filter empty strings from list")
    void testFilterEmptyStrings() {
        List<String> mixed = Arrays.asList("hello", "", "world", "", "java");
        List<String> result = mixed.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        
        assertEquals(3, result.size());
        assertEquals(Arrays.asList("hello", "world", "java"), result);
    }

    @Test
    @DisplayName("Map and filter combined operations")
    void testMapAndFilterCombined() {
        List<String> numbers = Arrays.asList("1", "2", "3", "invalid", "5");
        List<Integer> result = numbers.stream()
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(n -> n != null)
                .filter(n -> n > 2)
                .collect(Collectors.toList());
        
        assertEquals(2, result.size());
        assertEquals(Arrays.asList(3, 5), result);
    }

    @Test
    @DisplayName("Reduce stream to single value")
    void testReduceOperation() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        
        assertEquals(15, sum);
        
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        
        assertEquals(120, product);
    }

    @Test
    @DisplayName("Count elements in stream")
    void testCountOperation() {
        List<String> words = Arrays.asList("a", "bb", "ccc", "dd", "eeeee");
        long count = words.stream()
                .filter(w -> w.length() > 2)
                .count();
        
        assertEquals(2, count);
    }

    @Test
    @DisplayName("Collect to Map")
    void testCollectToMap() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );
        
        Map<String, Integer> nameToAge = people.stream()
                .collect(Collectors.toMap(
                        Person::getName,
                        Person::getAge
                ));
        
        assertEquals(3, nameToAge.size());
        assertEquals(25, nameToAge.get("Alice"));
        assertEquals(30, nameToAge.get("Bob"));
    }

    @Test
    @DisplayName("Collect to Set")
    void testCollectToSet() {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        Set<Integer> uniqueNumbers = numbers.stream()
                .collect(Collectors.toSet());
        
        assertEquals(4, uniqueNumbers.size());
        assertTrue(uniqueNumbers.contains(1));
        assertTrue(uniqueNumbers.contains(2));
        assertTrue(uniqueNumbers.contains(3));
        assertTrue(uniqueNumbers.contains(4));
    }

    @Test
    @DisplayName("Distinct elements")
    void testDistinctOperation() {
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana");
        List<String> distinctWords = words.stream()
                .distinct()
                .collect(Collectors.toList());
        
        assertEquals(3, distinctWords.size());
    }

    @Test
    @DisplayName("Limit stream elements")
    void testLimitOperation() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> limited = numbers.stream()
                .limit(3)
                .collect(Collectors.toList());
        
        assertEquals(3, limited.size());
        assertEquals(Arrays.asList(1, 2, 3), limited);
    }

    @Test
    @DisplayName("Skip stream elements")
    void testSkipOperation() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> skipped = numbers.stream()
                .skip(2)
                .collect(Collectors.toList());
        
        assertEquals(3, skipped.size());
        assertEquals(Arrays.asList(3, 4, 5), skipped);
    }

    @Test
    @DisplayName("FlatMap operation")
    void testFlatMapOperation() {
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );
        
        List<Integer> flattened = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        
        assertEquals(9, flattened.size());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), flattened);
    }

    @Test
    @DisplayName("Find operations")
    void testFindOperations() {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        
        Optional<String> first = words.stream().findFirst();
        assertTrue(first.isPresent());
        assertEquals("apple", first.get());
        
        Optional<String> any = words.stream().findAny();
        assertTrue(any.isPresent());
    }

    @Test
    @DisplayName("Match operations")
    void testMatchOperations() {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8);
        List<Integer> mixedNumbers = Arrays.asList(1, 2, 3, 4, 5);
        
        assertTrue(numbers.stream().allMatch(n -> n % 2 == 0));
        assertFalse(mixedNumbers.stream().allMatch(n -> n % 2 == 0));
        
        assertTrue(mixedNumbers.stream().anyMatch(n -> n % 2 == 0));
        assertFalse(Arrays.asList(1, 3, 5).stream().anyMatch(n -> n % 2 == 0));
        
        assertTrue(Arrays.asList(1, 3, 5).stream().noneMatch(n -> n % 2 == 0));
    }

    @Test
    @DisplayName("Peek operation for debugging")
    void testPeekOperation() {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        List<String> result = new ArrayList<>();
        
        words.stream()
                .peek(w -> result.add(w.toUpperCase()))
                .collect(Collectors.toList());
        
        assertEquals(3, result.size());
        assertTrue(result.contains("APPLE"));
    }

    @Test
    @DisplayName("Sorted with custom comparator")
    void testSortedWithComparator() {
        List<Person> people = Arrays.asList(
                new Person("Charlie", 25),
                new Person("Alice", 35),
                new Person("Bob", 30)
        );
        
        List<Person> sortedByName = people.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
        
        assertEquals("Alice", sortedByName.get(0).getName());
        assertEquals("Bob", sortedByName.get(1).getName());
        assertEquals("Charlie", sortedByName.get(2).getName());
    }

    @Test
    @DisplayName("Min and Max operations")
    void testMinMaxOperations() {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        
        Optional<Integer> minValue = numbers.stream().min(Integer::compareTo);
        assertTrue(minValue.isPresent());
        assertEquals(1, minValue.get());
        
        Optional<Integer> maxValue = numbers.stream().max(Integer::compareTo);
        assertTrue(maxValue.isPresent());
        assertEquals(9, maxValue.get());
    }

    @Test
    @DisplayName("String joining")
    void testStringJoining() {
        List<String> fruits = Arrays.asList("apple", "banana", "cherry");
        
        String joined = fruits.stream()
                .collect(Collectors.joining(", "));
        
        assertEquals("apple, banana, cherry", joined);
        
        String withPrefix = fruits.stream()
                .collect(Collectors.joining(", ", "[", "]"));
        
        assertEquals("[apple, banana, cherry]", withPrefix);
    }

    // Helper methods

    private static Map<Character, Long> expectedLetterFrequency() {
        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('c', 1L);
        expectedMap.put('s', 3L);
        expectedMap.put('D', 1L);
        expectedMap.put('u', 1L);
        expectedMap.put('i', 2L);
        expectedMap.put('n', 1L);
        expectedMap.put('o', 1L);
        return expectedMap;
    }

    private static String hashTagText() {
        return "Going for a long run can be #podcast quite liberating, a great "
                + "way to #Workout clear the mind. While on my run, I usually tune into my favorite #podcast This "
                + "is just one of #Workout the ways I practice #SelfCare Running isn't just about fitness, it's "
                + "also my #MeTime So no matter how busy life gets, remember to create some space for #personalGrowth and "
                + "never underestimate the power of a good #Workout Your #MentalHealth will thank you!";
    }
}
