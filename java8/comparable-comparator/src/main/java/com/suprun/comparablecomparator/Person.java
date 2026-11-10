package com.suprun.comparablecomparator;

/**
 * Person class implementing Comparable interface.
 * Demonstrates natural ordering by age.
 */
public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private double salary;

    /**
     * No-argument constructor.
     */
    public Person() {
    }

    /**
     * All-argument constructor.
     *
     * @param name the person's name
     * @param age the person's age
     * @param salary the person's salary
     */
    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    /**
     * Gets the name.
     *
     * @return the person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the person's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age.
     *
     * @return the person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age the person's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the salary.
     *
     * @return the person's salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary.
     *
     * @param salary the person's salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (Double.compare(person.salary, salary) != 0) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
