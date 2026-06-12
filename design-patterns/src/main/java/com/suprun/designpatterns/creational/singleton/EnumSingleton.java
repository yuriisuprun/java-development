package com.suprun.designpatterns.creational.singleton;

/**
 * EnumSingleton demonstrates the thread-safe enum-based Singleton pattern.
 * This is the recommended approach for implementing Singleton in Java.
 * It provides protection against reflection and serialization attacks.
 *
 * @author Yurii_Suprun
 */
public enum EnumSingleton {
    /**
     * The single instance of the enum.
     */
    INSTANCE;

    /**
     * Example method in the singleton.
     *
     * @return a string representation
     */
    public String getDescription() {
        return "I am an enum-based Singleton instance";
    }
}
