package com.suprun.designpatterns.creational.builder;

public class Car {

    private final String engine;
    private final int wheels;

    private final boolean airConditioning;
    private final boolean sunroof;

    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.airConditioning = builder.airConditioning;
        this.sunroof = builder.sunroof;
    }

    public static class CarBuilder {

        private final String engine;
        private final int wheels;

        private boolean airConditioning = false;
        private boolean sunroof = false;

        public CarBuilder(String engine, int wheels) {
            this.engine = engine;
            this.wheels = wheels;
        }

        public CarBuilder setAirConditioning(boolean airConditioning) {
            this.airConditioning = airConditioning;
            return this;
        }

        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car [engine=" + engine + ", wheels=" + wheels
                + ", airConditioning=" + airConditioning + ", sunroof=" + sunroof + "]";
    }
}

class Main {

    public static void main(String[] args) {

        Car car = new Car.CarBuilder("V8", 4)
                .setAirConditioning(true)
                .setSunroof(true)
                .build();

        System.out.println(car);
    }
}

