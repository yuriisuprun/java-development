package com.suprun.designpatterns.structural.decorator;

public class BasicCoffee implements Coffee {

    @Override
    public double getCost() {
        return 2.0;
    }

    @Override
    public String getDescription() {
        return "Basic coffee";
    }
}

