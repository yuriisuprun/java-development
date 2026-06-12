package com.suprun.designpatterns.creational.factorymethod;

/**
 * Dog is a concrete implementation of the Animal interface.
 *
 * @author Yurii_Suprun
 */
public class Dog implements Animal {

    @Override
    public void speak() {
        System.out.println("Woof! I'm a dog.");
    }

    @Override
    public String toString() {
        return "Dog{}";
    }
}
