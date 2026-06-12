package com.suprun.designpatterns.creational.singleton;

/**
 * Singleton class demonstrating the Singleton design pattern using double-checked locking.
 * This implementation is thread-safe and lazy-initialized.
 *
 * @author Yurii_Suprun
 */
public class Singleton {

    private static volatile Singleton instance = null;

    /**
     * Private constructor to prevent instantiation.
     * Throws an exception if someone tries to create a new instance via reflection.
     */
    private Singleton() {
        if (instance != null) {
            throw new IllegalStateException("Singleton instance already exists. Use getInstance() method.");
        }
    }

    /**
     * Gets the singleton instance using double-checked locking.
     *
     * @return the single instance of Singleton
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "instance=" + System.identityHashCode(this) +
                '}';
    }
}
