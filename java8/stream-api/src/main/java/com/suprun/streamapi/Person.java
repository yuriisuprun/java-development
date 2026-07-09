package com.suprun.streamapi;

import java.util.Objects;

/**
 * Represents a person with name, age, and nationality.
 * Immutable entity used for stream demonstrations.
 * 
 * @author Yurii_Suprun
 * @version 2.0
 */
public class Person {

    private final String name;
    private final int age;
    private final String nationality;

    /**
     * Constructs a person with all attributes.
     * 
     * @param name the person's name
     * @param age the person's age
     * @param nationality the person's nationality
     */
    public Person(String name, int age, String nationality) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.age = age;
        this.nationality = Objects.requireNonNull(nationality, "nationality cannot be null");
    }

    /**
     * Constructs a person with name and age (nationality defaults to empty).
     * 
     * @param name the person's name
     * @param age the person's age
     */
    public Person(String name, int age) {
        this(name, age, "");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
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
     * Checks if the person is a minor (age < 18).
     * 
     * @return true if minor, false otherwise
     */
    public boolean isMinor() {
        return age < 18;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && 
               Objects.equals(name, person.name) && 
               Objects.equals(nationality, person.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, nationality);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
