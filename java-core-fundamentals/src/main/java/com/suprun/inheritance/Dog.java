package com.suprun.inheritance;

/**
 * @author Yurii_Suprun
 */
class Dog extends Animal {

    public void bark() {
        System.out.println("Dog is barking");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }
}
