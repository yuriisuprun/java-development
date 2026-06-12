package com.suprun.designpatterns.creational.factorymethod;

/**
 * Demo class for the Factory Method design pattern.
 * Shows how to use a factory to create objects without specifying their exact classes.
 *
 * @author Yurii_Suprun
 */
public class Main {

    public static void main(String[] args) {
        // Create a dog using the factory
        Animal dog = AnimalFactory.createAnimal("dog");
        System.out.println("Created: " + dog);
        dog.speak();

        System.out.println();

        // Create a cat using the factory
        Animal cat = AnimalFactory.createAnimal("cat");
        System.out.println("Created: " + cat);
        cat.speak();

        System.out.println();

        // Demonstrate error handling for unknown animal type
        try {
            Animal unknown = AnimalFactory.createAnimal("bird");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}