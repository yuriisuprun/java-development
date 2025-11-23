package com.suprun.designpatterns.creational.factorymethod;

public class Cat implements Animal {

    @Override
    public void speak() {
        System.out.println("Meow!");
    }
}
