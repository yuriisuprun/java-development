package com.suprun.designpatterns.structural.decorator;

import java.util.Objects;

/**
 * CoffeeDecorator is the abstract decorator in the Decorator pattern.
 * It wraps a Coffee object and delegates requests to it.
 *
 * @author Yurii_Suprun
 */
public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

    /**
     * Constructs a CoffeeDecorator with a Coffee object.
     *
     * @param coffee the coffee to be decorated
     * @throws NullPointerException if coffee is null
     */
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = Objects.requireNonNull(coffee, "Coffee cannot be null");
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
}

