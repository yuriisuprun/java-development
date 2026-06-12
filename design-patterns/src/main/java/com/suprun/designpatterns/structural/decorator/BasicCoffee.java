package com.suprun.designpatterns.structural.decorator;

/**
 * BasicCoffee is the concrete component in the Decorator pattern.
 * It represents a simple coffee without any decorations.
 *
 * @author Yurii_Suprun
 */
public class BasicCoffee implements Coffee {

    private static final double BASE_COST = 2.0;
    private static final String BASE_DESCRIPTION = "Basic coffee";

    @Override
    public double getCost() {
        return BASE_COST;
    }

    @Override
    public String getDescription() {
        return BASE_DESCRIPTION;
    }
}

