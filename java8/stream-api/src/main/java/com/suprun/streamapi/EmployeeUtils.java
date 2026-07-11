package com.suprun.streamapi;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility class for Employee stream operations.
 * Demonstrates domain-specific stream patterns using generic CollectionUtils.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public final class EmployeeUtils {

    private EmployeeUtils() {
        // Prevent instantiation
    }

    /**
     * Finds the highest paid employee.
     * Delegates to generic CollectionUtils.findMax().
     * 
     * @param employees a collection of employees
     * @return the employee with highest salary
     * @throws IllegalArgumentException if collection is empty
     */
    public static Employee findHighestPaid(Collection<Employee> employees) {
        return CollectionUtils.findMax(employees, Comparator.comparingLong(Employee::getSalary));
    }

    /**
     * Finds the lowest paid employee.
     * Delegates to generic CollectionUtils.findMin().
     * 
     * @param employees a collection of employees
     * @return the employee with lowest salary
     * @throws IllegalArgumentException if collection is empty
     */
    public static Employee findLowestPaid(Collection<Employee> employees) {
        return CollectionUtils.findMin(employees, Comparator.comparingLong(Employee::getSalary));
    }

    /**
     * Calculates the total salary expense.
     * 
     * @param employees a collection of employees
     * @return total salary sum
     */
    public static long getTotalSalary(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .mapToLong(Employee::getSalary)
                        .sum();
    }

    /**
     * Calculates the average salary.
     * 
     * @param employees a collection of employees
     * @return average salary, or 0 if empty
     */
    public static double getAverageSalary(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .mapToLong(Employee::getSalary)
                        .average()
                        .orElse(0.0);
    }

    /**
     * Groups employees by position.
     * Delegates to generic CollectionUtils.groupBy().
     * 
     * @param employees a collection of employees
     * @return map with position as key and employee list as value
     */
    public static Map<String, List<Employee>> groupByPosition(Collection<Employee> employees) {
        return CollectionUtils.groupBy(employees, Employee::getPosition);
    }

    /**
     * Groups employees by salary remainder (for salary categorization).
     * 
     * @param employees a collection of employees
     * @param divisor the divisor for remainder calculation
     * @return map with remainder as key and employee list as value
     */
    public static Map<Long, List<Employee>> groupBySalaryRemainder(Collection<Employee> employees, int divisor) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .collect(Collectors.groupingBy(e -> e.getSalary() % divisor));
    }

    /**
     * Partitions employees by salary threshold.
     * Delegates to generic CollectionUtils.partition().
     * 
     * @param employees a collection of employees
     * @param salaryThreshold the salary threshold
     * @return map with Boolean (higher/lower) as key and employee list as value
     */
    public static Map<Boolean, List<Employee>> partitionBySalary(Collection<Employee> employees, long salaryThreshold) {
        return CollectionUtils.partition(employees, e -> e.getSalary() > salaryThreshold);
    }

    /**
     * Sorts employees by salary in ascending order.
     * Delegates to generic CollectionUtils.sortAscending().
     * 
     * @param employees a collection of employees
     * @return sorted list by salary
     */
    public static List<Employee> sortBySalaryAscending(Collection<Employee> employees) {
        return CollectionUtils.sortAscending(employees, Comparator.comparingLong(Employee::getSalary));
    }

    /**
     * Sorts employees by salary in descending order.
     * Delegates to generic CollectionUtils.sortDescending().
     * 
     * @param employees a collection of employees
     * @return sorted list by salary descending
     */
    public static List<Employee> sortBySalaryDescending(Collection<Employee> employees) {
        return CollectionUtils.sortDescending(employees, Comparator.comparingLong(Employee::getSalary));
    }

    /**
     * Sorts employees by name.
     * Delegates to generic CollectionUtils.sortAscending().
     * 
     * @param employees a collection of employees
     * @return sorted list by name
     */
    public static List<Employee> sortByName(Collection<Employee> employees) {
        return CollectionUtils.sortAscending(employees, Comparator.comparing(Employee::getName));
    }

    /**
     * Filters employees by minimum salary.
     * Delegates to generic CollectionUtils.filterByMinValue().
     * 
     * @param employees a collection of employees
     * @param minSalary the minimum salary threshold
     * @return list of employees earning at least minSalary
     */
    public static List<Employee> filterByMinSalary(Collection<Employee> employees, long minSalary) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .filter(e -> e.getSalary() >= minSalary)
                        .collect(Collectors.toList());
    }

    /**
     * Filters employees by maximum salary.
     * Delegates to generic CollectionUtils.filterByMaxValue().
     * 
     * @param employees a collection of employees
     * @param maxSalary the maximum salary threshold
     * @return list of employees earning at most maxSalary
     */
    public static List<Employee> filterByMaxSalary(Collection<Employee> employees, long maxSalary) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .filter(e -> e.getSalary() <= maxSalary)
                        .collect(Collectors.toList());
    }

    /**
     * Filters employees by position.
     * 
     * @param employees a collection of employees
     * @param position the position to filter by
     * @return list of employees in the specified position
     */
    public static List<Employee> filterByPosition(Collection<Employee> employees, String position) {
        Objects.requireNonNull(employees, "employees cannot be null");
        Objects.requireNonNull(position, "position cannot be null");
        return employees.stream()
                        .filter(e -> position.equals(e.getPosition()))
                        .collect(Collectors.toList());
    }

    /**
     * Extracts employee names.
     * Delegates to generic CollectionUtils.map().
     * 
     * @param employees a collection of employees
     * @return list of names
     */
    public static List<String> extractNames(Collection<Employee> employees) {
        return CollectionUtils.map(employees, Employee::getName);
    }
}

