package com.suprun.designpatterns.creational.factorymethod;

/**
 * Cat is a concrete implementation of the Animal interface.
 *
 * @author Yurii_Suprun
 */
public class Cat implements Animal {

    @Override
    public void speak() {
        System.out.println("Meow! I'm a cat.");
    }

    @Override
    public String toString() {
        return "Cat{}";
    }
}
