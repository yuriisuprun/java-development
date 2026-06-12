package com.suprun.designpatterns.creational.builder;

import java.util.Objects;

/**
 * Car class demonstrates the Builder design pattern.
 * This class is immutable and can only be created using the CarBuilder.
 *
 * @author Yurii_Suprun
 */
public class Car {

    private final String engine;
    private final int wheels;
    private final boolean airConditioning;
    private final boolean sunroof;

    /**
     * Private constructor - use CarBuilder to create instances.
     *
     * @param builder the builder containing the car configuration
     */
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.airConditioning = builder.airConditioning;
        this.sunroof = builder.sunroof;
    }

    /**
     * Builder class for creating Car instances.
     */
    public static class CarBuilder {

        private final String engine;
        private final int wheels;
        private boolean airConditioning = false;
        private boolean sunroof = false;

        /**
         * Constructs a CarBuilder with required parameters.
         *
         * @param engine the car's engine type
         * @param wheels the number of wheels
         * @throws NullPointerException if engine is null
         * @throws IllegalArgumentException if wheels is less than 1
         */
        public CarBuilder(String engine, int wheels) {
            this.engine = Objects.requireNonNull(engine, "Engine cannot be null");
            if (wheels < 1) {
                throw new IllegalArgumentException("Wheels must be at least 1");
            }
            this.wheels = wheels;
        }

        /**
         * Sets whether the car has air conditioning.
         *
         * @param airConditioning true if the car has air conditioning
         * @return this builder for method chaining
         */
        public CarBuilder setAirConditioning(boolean airConditioning) {
            this.airConditioning = airConditioning;
            return this;
        }

        /**
         * Sets whether the car has a sunroof.
         *
         * @param sunroof true if the car has a sunroof
         * @return this builder for method chaining
         */
        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        /**
         * Builds and returns the Car instance.
         *
         * @return the configured Car instance
         */
        public Car build() {
            return new Car(this);
        }
    }

    /**
     * Gets the engine type.
     *
     * @return the engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * Gets the number of wheels.
     *
     * @return the wheels
     */
    public int getWheels() {
        return wheels;
    }

    /**
     * Checks if the car has air conditioning.
     *
     * @return true if air conditioning is available
     */
    public boolean hasAirConditioning() {
        return airConditioning;
    }

    /**
     * Checks if the car has a sunroof.
     *
     * @return true if sunroof is available
     */
    public boolean hasSunroof() {
        return sunroof;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", wheels=" + wheels +
                ", airConditioning=" + airConditioning +
                ", sunroof=" + sunroof +
                '}';
    }
}

