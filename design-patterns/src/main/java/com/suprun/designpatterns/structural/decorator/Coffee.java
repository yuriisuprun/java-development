package com.suprun.designpatterns.structural.decorator;

/**
 * Coffee interface defines the contract for coffee objects.
 * This interface is used in the Decorator pattern to define the component interface.
 *
 * @author Yurii_Suprun
 */
public interface Coffee {

    /**
     * Gets the cost of the coffee.
     *
     * @return the cost
     */
    double getCost();

    /**
     * Gets the description of the coffee.
     *
     * @return the description
     */
    String getDescription();
}
