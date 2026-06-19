package com.suprun.coding;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link NumberOperations}
 *
 * @author Yurii_Suprun
 */
@DisplayName("Number Operations Tests")
public class NumberOperationsTest {

    private final NumberOperations numberOps = new NumberOperations();

    @ParameterizedTest
    @CsvSource({
            "123, 321",
            "-123, -321",
            "120, 21",
            "0, 0",
            "1, 1"
    })
    @DisplayName("Should reverse integer using string conversion")
    void testReverseInteger(int input, int expected) {
        assertEquals(expected, numberOps.reverseInteger(input));
    }

    @ParameterizedTest
    @CsvSource({
            "123, 321",
            "-123, -321",
            "120, 21",
            "0, 0",
            "1, 1"
    })
    @DisplayName("Should reverse integer without string conversion")
    void testReverseIntegerWithoutString(int input, int expected) {
        assertEquals(expected, numberOps.reverseIntegerWithoutString(input));
    }

    @Test
    @DisplayName("Should handle overflow in reverseIntegerWithoutString")
    void testReverseIntegerWithoutStringOverflow() {
        // Integer.MAX_VALUE = 2147483647, reversed = 7463847412 (overflows)
        assertEquals(0, numberOps.reverseIntegerWithoutString(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Should find missing number in sorted array")
    void testFindMissedNumber() {
        assertEquals(3, numberOps.findMissedNumber(new int[]{1, 2, 4, 5}));
        assertEquals(5, numberOps.findMissedNumber(new int[]{1, 2, 3, 4, 6, 7}));
    }

    @Test
    @DisplayName("Should return 0 when no number is missing")
    void testFindMissedNumberNoMissing() {
        assertEquals(0, numberOps.findMissedNumber(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    @DisplayName("Should find missing number using sum calculation")
    void testFindMissedNumberBySum() {
        assertEquals(3, numberOps.findMissedNumberBySum(new int[]{1, 2, 4, 5}));
        assertEquals(5, numberOps.findMissedNumberBySum(new int[]{1, 2, 3, 4, 6, 7}));
    }

    @Test
    @DisplayName("Should calculate sum of variable arguments")
    void testCountSum() {
        assertEquals(15, NumberOperations.countSum(1, 2, 3, 4, 5));
        assertEquals(0, NumberOperations.countSum());
        assertEquals(5, NumberOperations.countSum(5));
        assertEquals(10, NumberOperations.countSum(1, 2, 3, 4));
    }

    @Test
    @DisplayName("Should find maximum number in list")
    void testMaxNumber() {
        assertEquals(7, numberOps.maxNumber(java.util.Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        assertEquals(100, numberOps.maxNumber(java.util.Arrays.asList(1, 100, 50)));
        assertEquals(5, numberOps.maxNumber(java.util.Arrays.asList(5)));
    }

    @Test
    @DisplayName("Should throw exception for empty list in maxNumber")
    void testMaxNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> numberOps.maxNumber(java.util.List.of()));
    }

    @Test
    @DisplayName("Should sort zeros and ones in array")
    void testSortOnesZeros() {
        int[] result = numberOps.sortOnesZeros(new int[]{1, 0, 1, 0, 1});
        assertArrayEquals(new int[]{0, 0, 1, 1, 1}, result);

        int[] result2 = numberOps.sortOnesZeros(new int[]{0, 0, 0});
        assertArrayEquals(new int[]{0, 0, 0}, result2);

        int[] result3 = numberOps.sortOnesZeros(new int[]{1, 1, 1});
        assertArrayEquals(new int[]{1, 1, 1}, result3);
    }
}
