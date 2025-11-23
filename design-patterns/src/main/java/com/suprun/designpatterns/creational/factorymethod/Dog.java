package com.suprun.designpatterns.creational.factorymethod;

public class Dog implements Animal {

    @Override
    public void speak() {
        System.out.println("Woof!");
    }
}
