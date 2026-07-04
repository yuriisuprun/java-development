package com.suprun.comparablecomparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person class implementing Comparable interface.
 * Demonstrates natural ordering by age.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private double salary;

    /**
     * Natural ordering by age (ascending).
     * Override compareTo to define natural order.
     *
     * @param other the Person to compare with
     * @return negative if this person is younger, 0 if same age, positive if older
     */
    @Override
    public int compareTo(Person other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare with null");
        }
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
