package com.suprun.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Demonstration of Stream API capabilities and utilities.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public class Main {
    public static void main(String[] args) {
        StreamApi streamApi = new StreamApi();

        System.out.println("=== 1. String Operations ===");
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        System.out.println("Longest string: " + StreamUtils.findLongestString(words));
        System.out.println("Longest length: " + StreamUtils.getLongestStringLength(words));
        System.out.println("Shortest string: " + StreamUtils.findShortestString(words));
        System.out.println("Sum of lengths: " + StreamUtils.sumStringLengths(words));
        System.out.println("Average length: " + StreamUtils.averageStringLength(words));

        System.out.println("\n=== 2. String Filtering ===");
        List<String> minLength = StreamUtils.filterByMinLength(words, 5);
        System.out.println("Words with length >= 5: " + minLength);

        System.out.println("\n=== 3. Character Frequency ===");
        Map<Character, Long> frequencies = streamApi.countLettersInWord("Mississippi");
        System.out.println("Letter frequencies in 'Mississippi': " + frequencies);

        System.out.println("\n=== 4. Person Operations ===");
        List<Person> people = Arrays.asList(
            new Person("John", 25, "USA"),
            new Person("Jane", 30, "UK"),
            new Person("Jack", 20, "USA"),
            new Person("Jill", 35, "Canada"),
            new Person("Jim", 17, "USA")
        );

        System.out.println("\nOldest person: " + PersonUtils.findOldest(people));
        System.out.println("Youngest person: " + PersonUtils.findYoungest(people));
        System.out.println("Average age: " + PersonUtils.getAverageAge(people));
        System.out.println("Adults count: " + PersonUtils.countAdults(people));
        System.out.println("Minors count: " + PersonUtils.countMinors(people));

        System.out.println("\n=== 5. Sort People by Age ===");
        List<Person> sortedByAge = PersonUtils.sortByAgeAscending(people);
        sortedByAge.forEach(p -> System.out.println("  " + p.getName() + ": " + p.getAge()));

        System.out.println("\n=== 6. Filter People ===");
        List<Person> adults = PersonUtils.filterByMinAge(people, 18);
        System.out.println("Adults (age >= 18): ");
        adults.forEach(p -> System.out.println("  " + p));

        System.out.println("\n=== 7. Group People by Nationality ===");
        Map<String, List<Person>> byNationality = PersonUtils.groupByNationality(people);
        byNationality.forEach((nationality, persons) ->
            System.out.println("  " + nationality + ": " + PersonUtils.extractNames(persons))
        );

        System.out.println("\n=== 8. Partition People by Age ===");
        Map<Boolean, List<Person>> partitioned = PersonUtils.partitionByAge(people, 25);
        System.out.println("Older than 25: " + PersonUtils.extractNames(partitioned.get(true)));
        System.out.println("25 or younger: " + PersonUtils.extractNames(partitioned.get(false)));

        System.out.println("\n=== 9. Employee Operations ===");
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice", "dev", 60000L),
            new Employee(2, "Bob", "manager", 90000L),
            new Employee(3, "Charlie", "dev", 65000L),
            new Employee(4, "David", "hr", 55000L),
            new Employee(5, "Eve", "manager", 95000L)
        );

        System.out.println("\nHighest paid: " + EmployeeUtils.findHighestPaid(employees));
        System.out.println("Lowest paid: " + EmployeeUtils.findLowestPaid(employees));
        System.out.println("Total salary: " + EmployeeUtils.getTotalSalary(employees));
        System.out.println("Average salary: " + EmployeeUtils.getAverageSalary(employees));

        System.out.println("\n=== 10. Sort Employees by Salary ===");
        List<Employee> sortedSalary = EmployeeUtils.sortBySalaryDescending(employees);
        sortedSalary.stream()
                    .limit(3)
                    .forEach(e -> System.out.println("  " + e.getName() + ": $" + e.getSalary()));

        System.out.println("\n=== 11. Group Employees by Position ===");
        Map<String, List<Employee>> byPosition = EmployeeUtils.groupByPosition(employees);
        byPosition.forEach((position, emps) ->
            System.out.println("  " + position + ": " + EmployeeUtils.extractNames(emps))
        );

        System.out.println("\n=== 12. Filter by Salary ===");
        List<Employee> highEarners = EmployeeUtils.filterByMinSalary(employees, 90000L);
        System.out.println("Earning >= $90,000: " + EmployeeUtils.extractNames(highEarners));

        System.out.println("\n=== 13. Hashtag Frequency ===");
        String text = "I love #Java and #Java streams. #Streams are #powerful!";
        List<String> hashtags = streamApi.getSortedHashCodes(text);
        System.out.println("Hashtags by frequency: " + hashtags);
    }
}
