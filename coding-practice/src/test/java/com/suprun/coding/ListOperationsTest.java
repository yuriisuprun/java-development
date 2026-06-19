package com.suprun.coding;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ListOperations}
 *
 * @author Yurii_Suprun
 */
@DisplayName("List Operations Tests")
public class ListOperationsTest {

    @Test
    @DisplayName("Should find first duplicate using HashMap")
    void testGetDuplicate() {
        assertEquals(4, new ListOperations().getDuplicate(Arrays.asList(2, 4, 5, 4, 6)));
        assertEquals(0, new ListOperations().getDuplicate(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("Should find first duplicate using Map approach")
    void testGetDuplicateWithMap() {
        assertEquals(4, new ListOperations().getDuplicateWithMap(Arrays.asList(2, 4, 5, 4, 6)));
        assertEquals(0, new ListOperations().getDuplicateWithMap(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("Should find first duplicate using Stream API")
    void testGetDuplicateUsingStreamApi() {
        assertEquals(4, new ListOperations().getDuplicateUsingStreamApi(Arrays.asList(2, 4, 5, 4, 6)));
        assertEquals(0, new ListOperations().getDuplicateUsingStreamApi(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("Should find first duplicate using Array approach")
    void testGetDuplicateWithArray() {
        assertEquals(4, new ListOperations().getDuplicateWithArray(Arrays.asList(2, 4, 5, 4, 6)));
    }

    @Test
    @DisplayName("Should throw exception when no duplicate found")
    void testGetDuplicateWithArrayThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new ListOperations().getDuplicateWithArray(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("Should find all duplicates")
    void testFindDuplicates() {
        List<Integer> input = Arrays.asList(2, 4, 8, 8, 90, 20, 20, 5, 5);
        List<Integer> result = ListOperations.findDuplicates(input);
        assertEquals(3, result.size());
        assertTrue(result.contains(8));
        assertTrue(result.contains(20));
        assertTrue(result.contains(5));
    }

    @Test
    @DisplayName("Should handle empty list in findDuplicates")
    void testFindDuplicatesEmpty() {
        List<Integer> result = ListOperations.findDuplicates(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should collect duplicate numbers with their counts")
    void testCollectNumbers() {
        List<Integer> input = List.of(2, 3, 3, 3, 4, 4, 5);
        Map<Integer, Long> result = ListOperations.collectNumbers(input);
        assertEquals(3, result.get(3).longValue());
        assertEquals(2, result.get(4).longValue());
        assertFalse(result.containsKey(2)); // 2 appears only once
        assertFalse(result.containsKey(5)); // 5 appears only once
    }

    @Test
    @DisplayName("Should filter even numbers")
    void testFilterEvenNumbers() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> result = new ListOperations().filterEvenNumbers(input);
        assertEquals(List.of(2, 4, 6), result);
    }

    @Test
    @DisplayName("Should merge and sort two arrays")
    void testMergeSorted() {
        int[] arr1 = {1, 5, 9};
        int[] arr2 = {4, 6, 7, 10};
        int[] result = ListOperations.mergeSorted(arr1, arr2);
        assertArrayEquals(new int[]{1, 4, 5, 6, 7, 9, 10}, result);
    }

    @Test
    @DisplayName("Should find two-sum pair indices")
    void testToSum() {
        int[] result1 = new ListOperations().toSum(new int[]{2, 4, 5, 6}, 8);
        assertArrayEquals(new int[]{0, 3}, result1);

        int[] result2 = new ListOperations().toSum(new int[]{11, 7, 2, 15}, 9);
        assertArrayEquals(new int[]{1, 2}, result2);

        int[] result3 = new ListOperations().toSum(new int[]{3, 3}, 6);
        assertArrayEquals(new int[]{0, 1}, result3);
    }

    @Test
    @DisplayName("Should throw exception when target sum not achievable")
    void testToSumThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new ListOperations().toSum(new int[]{1, 2, 3}, 10));
    }
}
