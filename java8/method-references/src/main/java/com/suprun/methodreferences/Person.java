package com.suprun.methodreferences;

import java.util.Objects;

/**
 * Person entity demonstrating constructor references and immutability patterns.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public class Person {
    private final String name;
    private final int age;

    /**
     * Default constructor creating an unknown person.
     */
    public Person() {
        this("Unknown", 0);
    }

    /**
     * Constructor with name only.
     * 
     * @param name the person's name
     */
    public Person(String name) {
        this(name, 0);
    }

    /**
     * Full constructor with name and age.
     * 
     * @param name the person's name
     * @param age the person's age
     */
    public Person(String name, int age) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * Checks if the person is an adult (age >= 18).
     * 
     * @return true if adult, false otherwise
     */
    public boolean isAdult() {
        return age >= 18;
    }

    /**
     * Returns a formatted representation of the person.
     * 
     * @return formatted string
     */
    public String toFormattedString() {
        return String.format("%s (%d years old)", name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}
