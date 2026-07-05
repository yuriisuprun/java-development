package com.suprun.otherfeaturesofJava8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Default and Static Methods Tests")
class DefaultStaticMethodsTest {

    private Calculator simpleCalculator;
    private Calculator advancedCalculator;

    @BeforeEach
    void setUp() {
        simpleCalculator = new SimpleCalculator();
        advancedCalculator = new AdvancedCalculator();
    }

    @Test
    @DisplayName("Abstract method implementation")
    void testAbstractMethod() {
        assertEquals(8, simpleCalculator.calculate(3, 5)); // 3 + 5
        assertEquals(15, advancedCalculator.calculate(3, 5)); // 3 * 5
    }

    @Test
    @DisplayName("Default method - add")
    void testDefaultAdd() {
        assertEquals(8, simpleCalculator.add(3, 5));
        assertEquals(8, advancedCalculator.add(3, 5));
    }

    @Test
    @DisplayName("Default method - subtract")
    void testDefaultSubtract() {
        assertEquals(-2, simpleCalculator.subtract(3, 5));
        assertEquals(2, simpleCalculator.subtract(5, 3));
    }

    @Test
    @DisplayName("Default method - multiply")
    void testDefaultMultiply() {
        assertEquals(15, simpleCalculator.multiply(3, 5));
        assertEquals(0, simpleCalculator.multiply(0, 5));
    }

    @Test
    @DisplayName("Default method - divide")
    void testDefaultDivide() {
        assertEquals(2, simpleCalculator.divide(10, 5));
        assertEquals(3, advancedCalculator.divide(10, 3));
    }

    @Test
    @DisplayName("Default method - divide by zero")
    void testDefaultDivideByZero() {
        assertThrows(ArithmeticException.class, () -> simpleCalculator.divide(10, 0));
        assertThrows(ArithmeticException.class, () -> advancedCalculator.divide(10, 0));
    }

    @Test
    @DisplayName("Override divide method")
    void testOverrideDivide() {
        // Both should handle division, but may have different error messages
        assertEquals(2, simpleCalculator.divide(10, 5));
        assertEquals(3, advancedCalculator.divide(10, 3));
    }

    @Test
    @DisplayName("Static method - absoluteDifference")
    void testStaticAbsoluteDifference() {
        assertEquals(2, Calculator.absoluteDifference(5, 3));
        assertEquals(2, Calculator.absoluteDifference(3, 5));
        assertEquals(0, Calculator.absoluteDifference(5, 5));
    }

    @Test
    @DisplayName("Static method - power")
    void testStaticPower() {
        assertEquals(8.0, Calculator.power(2, 3));
        assertEquals(1.0, Calculator.power(5, 0));
        assertEquals(0.5, Calculator.power(2, -1));
    }

    @Test
    @DisplayName("Static method - max")
    void testStaticMax() {
        assertEquals(5, Calculator.max(3, 5));
        assertEquals(5, Calculator.max(5, 3));
        assertEquals(5, Calculator.max(5, 5));
    }

    @Test
    @DisplayName("Static method - min")
    void testStaticMin() {
        assertEquals(3, Calculator.min(3, 5));
        assertEquals(3, Calculator.min(5, 3));
        assertEquals(5, Calculator.min(5, 5));
    }

    @Test
    @DisplayName("Mix default and abstract methods")
    void testMixedUsage() {
        Calculator calc = simpleCalculator;
        int result = calc.add(calc.calculate(2, 3), calc.subtract(10, 5)); // (2+3) + (10-5) = 5 + 5 = 10
        assertEquals(10, result);
    }

    @Test
    @DisplayName("Multiple default methods chaining")
    void testMethodChaining() {
        Calculator calc = new SimpleCalculator();
        int result = calc.add(
                calc.multiply(2, 3),      // 2 * 3 = 6
                calc.subtract(10, 2)      // 10 - 2 = 8
        );                                // 6 + 8 = 14
        assertEquals(14, result);
    }
}
