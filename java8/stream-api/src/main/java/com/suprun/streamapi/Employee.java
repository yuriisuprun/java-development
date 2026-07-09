package com.suprun.streamapi;

import java.util.Objects;

/**
 * Represents an employee with id, name, position, and salary.
 * Immutable entity used for stream demonstrations.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public class Employee {
    private final int id;
    private final String name;
    private final String position;
    private final Long salary;

    /**
     * Constructs an employee with all attributes.
     * 
     * @param id the employee's unique identifier
     * @param name the employee's name
     * @param position the employee's job position
     * @param salary the employee's salary
     */
    public Employee(int id, String name, String position, Long salary) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.position = Objects.requireNonNull(position, "position cannot be null");
        this.salary = Objects.requireNonNull(salary, "salary cannot be null");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Long getSalary() {
        return salary;
    }

    /**
     * Checks if the employee is a manager.
     * 
     * @return true if position is "manager", false otherwise
     */
    public boolean isManager() {
        return "manager".equalsIgnoreCase(position);
    }

    /**
     * Checks if the employee is a developer.
     * 
     * @return true if position is "dev", false otherwise
     */
    public boolean isDeveloper() {
        return "dev".equalsIgnoreCase(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && 
               Objects.equals(name, employee.name) && 
               Objects.equals(position, employee.position) && 
               Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
