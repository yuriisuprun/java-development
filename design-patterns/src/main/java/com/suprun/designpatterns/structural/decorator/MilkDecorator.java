package com.suprun.designpatterns.structural.decorator;

/**
 * MilkDecorator adds milk to a coffee and increases its cost.
 * This is a concrete decorator in the Decorator pattern.
 *
 * @author Yurii_Suprun
 */
public class MilkDecorator extends CoffeeDecorator {

    private static final double MILK_COST = 0.5;
    private static final String MILK_SUFFIX = ", milk";

    /**
     * Constructs a MilkDecorator wrapping a Coffee object.
     *
     * @param coffee the coffee to be decorated with milk
     */
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + MILK_COST;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + MILK_SUFFIX;
    }
}

