package com.suprun.coding;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ArrayTasks}
 *
 * @author Yurii_Suprun
 */
@DisplayName("Array Tasks Tests")
public class ArrayTasksTest {

    @Test
    @DisplayName("Should reverse generic array")
    void testReverseArray() {
        Integer[] input = {1, 2, 3, 4, 5};
        Integer[] result = ArrayTasks.reverseArray(input);
        assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, result);
    }

    @Test
    @DisplayName("Should reverse string array")
    void testReverseStringArray() {
        String[] input = {"Alice", "Bob", "Charlie"};
        String[] result = ArrayTasks.reverseArray(input);
        assertArrayEquals(new String[]{"Charlie", "Bob", "Alice"}, result);
    }

    @Test
    @DisplayName("Should throw exception for null array")
    void testReverseArrayThrowsExceptionNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTasks.reverseArray(null));
    }

    @Test
    @DisplayName("Should throw exception for empty array")
    void testReverseArrayThrowsExceptionEmpty() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTasks.reverseArray(new Integer[0]));
    }

    @Test
    @DisplayName("Should reverse int array using Stream API")
    void testReverseIntArrayWithStream() {
        int[] input = {1, 2, 3, 4, 5};
        int[] result = ArrayTasks.reverseIntArrayWithStream(input);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, result);
    }

    @Test
    @DisplayName("Should find min and max elements in array")
    void testFindMaximumAndMinimumElement() {
        int[] input = {6, 7, 8, -3, 110, 11};
        int[] result = ArrayTasks.findMaximumAndMinimumElement(input);
        assertEquals(-3, result[0]); // min
        assertEquals(110, result[1]); // max
    }

    @Test
    @DisplayName("Should throw exception for null array in min/max")
    void testFindMaximumAndMinimumElementThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTasks.findMaximumAndMinimumElement(null));
    }

    @Test
    @DisplayName("Should sort array using bubble sort")
    void testBubbleSortArray() {
        int[] input = {5, 2, 8, 1, 9};
        int[] result = ArrayTasks.bubbleSortArray(input);
        assertArrayEquals(new int[]{1, 2, 5, 8, 9}, result);
    }

    @Test
    @DisplayName("Should handle already sorted array in bubble sort")
    void testBubbleSortArrayAlreadySorted() {
        int[] input = {1, 2, 3, 4, 5};
        int[] result = ArrayTasks.bubbleSortArray(input);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    @DisplayName("Should throw exception for null array in bubble sort")
    void testBubbleSortArrayThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTasks.bubbleSortArray(null));
    }

    @Test
    @DisplayName("Should find unique numbers")
    void testGetUniqueNumbers() {
        Integer[] input = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        List<Integer> result = ArrayTasks.getUniqueNumbers(input);
        assertEquals(2, result.size()); // Only 1 and 4 appear once
        assertTrue(result.contains(1));
        assertTrue(result.contains(4));
        assertFalse(result.contains(2)); // 2 appears twice
        assertFalse(result.contains(3)); // 3 appears three times
        assertFalse(result.contains(5)); // 5 appears twice
    }

    @Test
    @DisplayName("Should throw exception for null array in getUniqueNumbers")
    void testGetUniqueNumbersThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTasks.getUniqueNumbers(null));
    }

    @Test
    @DisplayName("Should find duplicate numbers")
    void testGetDuplicateNumbers() {
        Integer[] input = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        List<Integer> result = ArrayTasks.getDuplicateNumbers(input);
        assertEquals(3, result.size());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(5));
        assertFalse(result.contains(1)); // 1 appears only once
        assertFalse(result.contains(4)); // 4 appears only once
    }

    @Test
    @DisplayName("Should throw exception for null array in getDuplicateNumbers")
    void testGetDuplicateNumbersThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTasks.getDuplicateNumbers(null));
    }
}
