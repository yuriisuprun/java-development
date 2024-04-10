package com.suprun.algorithms;

import org.junit.jupiter.api.BeforeEach;
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
        int [] expectedArray = {104, 85, 67, 4, 5, 25};
        int [] actualArray = algorithms.reverseArray(numbers);

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
}


