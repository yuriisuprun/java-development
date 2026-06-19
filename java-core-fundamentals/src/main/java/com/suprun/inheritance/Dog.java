package com.suprun.inheritance;

/**
 * Dog class representing a dog, extending the Animal base class.
 * This class demonstrates method overriding and inheritance hierarchy.
 *
 * @author Yurii_Suprun
 */
public class Dog extends Animal {

    /**
     * Dog-specific behavior - barking.
     */
    public void bark() {
        System.out.println("Dog is barking");
    }

    /**
     * Overrides the eat method with dog-specific implementation.
     */
    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }
}
