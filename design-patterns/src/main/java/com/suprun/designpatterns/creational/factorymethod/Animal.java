package com.suprun.designpatterns.creational.factorymethod;

/**
 * Animal interface defines the contract for concrete animal implementations.
 * This interface is used as the product type in the Factory Method pattern.
 *
 * @author Yurii_Suprun
 */
public interface Animal {

    /**
     * Makes the animal speak/produce sound.
     */
    void speak();
}
