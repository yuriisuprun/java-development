package com.suprun.designpatterns.structural.decorator;

/**
 * SugarDecorator adds sugar to a coffee and increases its cost.
 * This is a concrete decorator in the Decorator pattern.
 *
 * @author Yurii_Suprun
 */
public class SugarDecorator extends CoffeeDecorator {

    private static final double SUGAR_COST = 0.2;
    private static final String SUGAR_SUFFIX = ", sugar";

    /**
     * Constructs a SugarDecorator wrapping a Coffee object.
     *
     * @param coffee the coffee to be decorated with sugar
     */
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + SUGAR_COST;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + SUGAR_SUFFIX;
    }
}

