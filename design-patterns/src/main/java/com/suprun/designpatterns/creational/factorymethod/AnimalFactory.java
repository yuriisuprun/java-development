package com.suprun.designpatterns.creational.factorymethod;

public class AnimalFactory {

    public static Animal createAnimal(String type) {
        if (type == null) return null;

        return switch (type.toLowerCase()) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> throw new IllegalArgumentException("Unknown animal type " + type);
        };
    }
}

