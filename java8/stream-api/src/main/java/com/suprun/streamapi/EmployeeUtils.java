package com.suprun.streamapi;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility class for Employee stream operations.
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
     * 
     * @param employees a collection of employees
     * @return the employee with highest salary
     * @throws IllegalArgumentException if collection is empty
     */
    public static Employee findHighestPaid(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .max(Comparator.comparingLong(Employee::getSalary))
                        .orElseThrow(() -> new IllegalArgumentException("employees collection cannot be empty"));
    }

    /**
     * Finds the lowest paid employee.
     * 
     * @param employees a collection of employees
     * @return the employee with lowest salary
     * @throws IllegalArgumentException if collection is empty
     */
    public static Employee findLowestPaid(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .min(Comparator.comparingLong(Employee::getSalary))
                        .orElseThrow(() -> new IllegalArgumentException("employees collection cannot be empty"));
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
     * 
     * @param employees a collection of employees
     * @return map with position as key and employee list as value
     */
    public static Map<String, List<Employee>> groupByPosition(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .collect(Collectors.groupingBy(Employee::getPosition));
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
     * 
     * @param employees a collection of employees
     * @param salaryThreshold the salary threshold
     * @return map with Boolean (higher/lower) as key and employee list as value
     */
    public static Map<Boolean, List<Employee>> partitionBySalary(Collection<Employee> employees, long salaryThreshold) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .collect(Collectors.partitioningBy(e -> e.getSalary() > salaryThreshold));
    }

    /**
     * Sorts employees by salary in ascending order.
     * 
     * @param employees a collection of employees
     * @return sorted list by salary
     */
    public static List<Employee> sortBySalaryAscending(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .sorted(Comparator.comparingLong(Employee::getSalary))
                        .collect(Collectors.toList());
    }

    /**
     * Sorts employees by salary in descending order.
     * 
     * @param employees a collection of employees
     * @return sorted list by salary descending
     */
    public static List<Employee> sortBySalaryDescending(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .sorted(Comparator.comparingLong(Employee::getSalary).reversed())
                        .collect(Collectors.toList());
    }

    /**
     * Sorts employees by name.
     * 
     * @param employees a collection of employees
     * @return sorted list by name
     */
    public static List<Employee> sortByName(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .sorted(Comparator.comparing(Employee::getName))
                        .collect(Collectors.toList());
    }

    /**
     * Filters employees by minimum salary.
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
     * 
     * @param employees a collection of employees
     * @return list of names
     */
    public static List<String> extractNames(Collection<Employee> employees) {
        Objects.requireNonNull(employees, "employees cannot be null");
        return employees.stream()
                        .map(Employee::getName)
                        .collect(Collectors.toList());
    }
}
