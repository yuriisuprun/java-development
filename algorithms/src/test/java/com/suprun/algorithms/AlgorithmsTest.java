package com.suprun.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test class for {@link Algorithms}
 *
 * @author Yurii_Suprun
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlgorithmsTest {

    private Algorithms algorithms;

    @BeforeEach
    void setUp() {
        algorithms = new Algorithms();
    }

    @Test
    @Order(1)
    void testBubbleSortAlgorithm() {
        int[] originalArray = {3, 60, 35, 2, 45, 320, 5};
        int[] expectedArray = {2, 3, 5, 35, 45, 60, 320};
        int[] actualArray = algorithms.bubbleSort(originalArray);

        assertEquals(Arrays.toString(expectedArray), Arrays.toString(actualArray));
    }

    @Test
    @Order(2)
    void testReverseArray() {
        int[] numbers = {25, 5, 4, 67, 85, 104};
        int[] expectedArray = {104, 85, 67, 4, 5, 25};
        int[] actualArray = algorithms.reverseArray(numbers);

        assertEquals(Arrays.toString(expectedArray), Arrays.toString(actualArray));
    }

    @Test
    @Order(3)
    void testFindMinInArray() {
        int[] numbers = {25, 5, 4, 67, 85, 104};
        Integer Integer = 4;
        int actualMin = algorithms.findMinInArray(numbers);

        assertEquals(Integer, actualMin);
    }

    @Test
    @Order(4)
    void testFindMinInArrayGenericComparable() {
        Integer[] numbers = {25, 5, 4, 67, 85, 104};
        Integer Integer = 4;
        int actualMin = algorithms.findMinInArrayGenericComparable(numbers);

        assertEquals(Integer, actualMin);
    }

    @Test
    @Order(4)
    void testFindMinInArrayGenericComparator() {
        Integer[] numbers = {25, 5, 4, 67, 85, 104};
        Integer Integer = 4;
        int actualMin = algorithms.findMinInArrayGenericComparator(numbers, (o1, o2) -> o1.compareTo(o2));

        assertEquals(Integer, actualMin);
    }

    @Test
    @Order(5)
    void testIsTextPalindrome_true() {
        String text = "A man, a plan, a canal, Panama!";
        boolean result = algorithms.isTextPalindrome(text);

        assertTrue(result);
    }

    @Test
    @Order(6)
    void testIsTextPalindrome_false() {
        String text = "Banana";
        boolean result = algorithms.isTextPalindrome(text);

        assertFalse(result);
    }

    @Test
    @Order(7)
    void testReversePositiveInteger() {
        int originalInt = 123;
        int result = algorithms.reverseInteger(originalInt);

        assertEquals(321, result);
    }

    @Test
    @Order(8)
    void testReverseNegativeInteger() {
        int originalInt = -123;
        int result = algorithms.reverseInteger(originalInt);

        assertEquals(-321, result);
    }

    @Test
    @Order(9)
    void testReverseInteger() {
        int originalInt = 120;
        int result = algorithms.reverseInteger(originalInt);

        assertEquals(21, result);
    }

    @Test
    @Order(10)
    void testReversePositiveIntegerWithoutString() {
        int originalInt = 2147483647;
        int result = algorithms.reverseIntegerWithoutString(originalInt);

        assertEquals(0, result);
    }

    @Test
    @Order(11)
    void testReverseNegativeIntegerWithoutString() {
        int originalInt = -2147483648;
        int result = algorithms.reverseIntegerWithoutString(originalInt);

        assertEquals(0, result);
    }

    @Test
    @Order(12)
    void testReverseIntegerWithoutString() {
        int originalInt = 120;
        int result = algorithms.reverseIntegerWithoutString(originalInt);

        assertEquals(21, result);
    }

    @Test
    @Order(13)
    void testFindMissedNumber() {
        int[] integers = {4, 5, 6, 7, 8, 10, 11};
        int result = algorithms.findMissedNumber(integers);

        assertEquals(9, result);
    }

    @Test
    @Order(14)
    void testFindMissedNumberBySum() {
        int[] integers = {4, 5, 6, 8, 9, 10, 11};
        int result = algorithms.findMissedNumberBySum(integers);

        assertEquals(7, result);
    }

    @Test
    @Order(15)
    @DisplayName("insertionSort method returns correct sorted array")
    void testSortArray() {
        int[] integers = {5, 2, 4, 6, 1};
        int[] expected = {1, 2, 4, 5, 6};
        int[] actual = algorithms.insertionSort(integers);

        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    @Order(16)
    @DisplayName("areAnagrams returns correct value")
    void testAreAnagrams_true() {
        boolean actual1 = algorithms.areAnagrams("HEART", "EARTH");
        boolean actual2 = algorithms.areAnagrams("LISTEN", "SILENT");

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    @Order(17)
    @DisplayName("areAnagrams returns correct value")
    void testAreAnagrams_false() {
        boolean actual1 = algorithms.areAnagrams("HEART", "EARTH2");
        boolean actual2 = algorithms.areAnagrams("LISTEN", "SILENT2");

        assertFalse(actual1);
        assertFalse(actual2);
    }

    @Test
    @Order(18)
    @DisplayName("areAnagrams returns correct value")
    void testAreAnagramsOopApproach_true() {
        boolean actual1 = algorithms.areAnagramsOopApproach("HEART", "EARTH");
        boolean actual2 = algorithms.areAnagramsOopApproach("LISTEN", "SILENT");

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    @Order(19)
    @DisplayName("areAnagrams returns correct value")
    void testAreAnagramsOopApproach_false() {
        boolean actual1 = algorithms.areAnagramsOopApproach("HEART", "EARTH2");
        boolean actual2 = algorithms.areAnagramsOopApproach("LISTEN", "SILENT2");

        assertFalse(actual1);
        assertFalse(actual2);
    }
}