package com.suprun.designpatterns.creational.factorymethod;

import java.util.Objects;

/**
 * AnimalFactory creates different types of animals using the Factory Method pattern.
 * This factory encapsulates the creation logic for animal objects.
 *
 * @author Yurii_Suprun
 */
public class AnimalFactory {

    /**
     * Creates an animal of the specified type.
     *
     * @param type the type of animal to create ("dog", "cat")
     * @return the created animal instance
     * @throws NullPointerException if type is null
     * @throws IllegalArgumentException if type is unknown
     */
    public static Animal createAnimal(String type) {
        Objects.requireNonNull(type, "Animal type cannot be null");

        return switch (type.toLowerCase().trim()) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> throw new IllegalArgumentException("Unknown animal type: " + type);
        };
    }
}

