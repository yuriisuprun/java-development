package com.suprun.interfaces;

/**
 * MyClass is a concrete implementation of the {@link MyInterface} interface.
 * It provides an implementation for the abstract method defined in the interface.
 *
 * @author Yurii_Suprun
 */
public class MyClass implements MyInterface {
    /**
     * Provides concrete implementation for the abstract method from MyInterface.
     */
    @Override
    public void abstractMethod() {
        System.out.println("Abstract method implementation");
    }
}
