package com.suprun.designpatterns.creational.singleton;

/**
 * Demo class for the Singleton design pattern.
 * Demonstrates both classic Singleton and Enum-based Singleton implementations.
 *
 * @author Yurii_Suprun
 */
public class SingletonMain {
    public static void main(String[] args) {
        // Testing classic Singleton with double-checked locking
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println("Classic Singleton - Same instance: " + (instance1 == instance2));
        System.out.println("Instance 1: " + instance1);
        System.out.println("Instance 2: " + instance2);

        System.out.println();

        // Testing enum-based Singleton
        EnumSingleton enumInstance1 = EnumSingleton.INSTANCE;
        EnumSingleton enumInstance2 = EnumSingleton.INSTANCE;
        System.out.println("Enum Singleton - Same instance: " + (enumInstance1 == enumInstance2));
        System.out.println("Enum Instance 1: " + enumInstance1);
        System.out.println("Enum Instance 2: " + enumInstance2);
        System.out.println("Enum Description: " + enumInstance1.getDescription());
    }
}