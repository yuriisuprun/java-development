package com.suprun.otherfeaturesofJava8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Iteration Features Tests")
class IterationFeaturesTest {

    private IterationFeatures iterationFeatures;
    private List<String> strings;
    private List<Integer> numbers;
    private Map<String, Integer> map;

    @BeforeEach
    void setUp() {
        iterationFeatures = new IterationFeatures();
        strings = Arrays.asList("apple", "banana", "cherry", "date");
        numbers = Arrays.asList(1, 2, 3, 4, 5);
        map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
    }

    @Test
    @DisplayName("forEach with list")
    void testForEachList() {
        AtomicInteger count = new AtomicInteger(0);
        iterationFeatures.forEachWithAction(strings, s -> count.incrementAndGet());
        assertEquals(4, count.get());
    }

    @Test
    @DisplayName("forEach with array")
    void testForEachArray() {
        String[] array = {"a", "b", "c"};
        AtomicInteger count = new AtomicInteger(0);
        iterationFeatures.forEachWithAction(Arrays.asList(array), s -> count.incrementAndGet());
        assertEquals(3, count.get());
    }

    @Test
    @DisplayName("forEach with index")
    void testForEachWithIndex() {
        List<String> result = iterationFeatures.forEachWithIndex(strings);
        assertEquals(4, result.size());
        assertEquals("[0] apple", result.get(0));
        assertEquals("[3] date", result.get(3));
    }

    @Test
    @DisplayName("forEach on map entries")
    void testForEachMapEntries() {
        AtomicInteger count = new AtomicInteger(0);
        map.forEach((key, value) -> count.incrementAndGet());
        assertEquals(3, count.get());
    }

    @Test
    @DisplayName("forEach on map keys")
    void testForEachMapKeys() {
        AtomicInteger count = new AtomicInteger(0);
        iterationFeatures.forEachMapKeys(map);
        assertEquals(3, map.keySet().size());
    }

    @Test
    @DisplayName("forEach on map values")
    void testForEachMapValues() {
        List<Integer> values = new ArrayList<>();
        map.values().forEach(values::add);
        assertEquals(3, values.size());
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
        assertTrue(values.contains(3));
    }

    @Test
    @DisplayName("Format list items with forEach")
    void testFormatListItems() {
        List<String> formatted = iterationFeatures.formatListItems(strings);
        assertEquals(4, formatted.size());
        assertEquals("APPLE", formatted.get(0));
        assertEquals("DATE", formatted.get(3));
    }

    @Test
    @DisplayName("Count total characters in strings")
    void testCountTotalCharacters() {
        int total = iterationFeatures.countTotalCharacters(strings);
        // apple(5) + banana(6) + cherry(6) + date(4) = 21
        assertEquals(21, total);
    }

    @Test
    @DisplayName("Filter and collect with forEach")
    void testFilterAndCollect() {
        List<Integer> filtered = iterationFeatures.filterAndCollect(numbers, 3);
        assertEquals(2, filtered.size()); // 4, 5
        assertTrue(filtered.contains(4));
        assertTrue(filtered.contains(5));
    }

    @Test
    @DisplayName("Group by length")
    void testGroupByLength() {
        List<String> testStrings = Arrays.asList("a", "ab", "abc", "ab", "a");
        Map<Integer, List<String>> grouped = iterationFeatures.groupByLength(testStrings);

        assertEquals(3, grouped.size());
        assertEquals(2, grouped.get(1).size()); // "a" appears twice
        assertEquals(2, grouped.get(2).size()); // "ab" appears twice
        assertEquals(1, grouped.get(3).size()); // "abc" appears once
    }

    @Test
    @DisplayName("RemoveIf with predicate")
    void testRemoveIf() {
        List<Integer> testNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        iterationFeatures.removeIf(testNumbers, n -> n > 3);

        assertEquals(3, testNumbers.size());
        assertFalse(testNumbers.contains(4));
        assertFalse(testNumbers.contains(5));
    }

    @Test
    @DisplayName("ReplaceAll with mapper")
    void testReplaceAll() {
        List<Integer> testNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        iterationFeatures.replaceAll(testNumbers, n -> n * 2);

        assertEquals(5, testNumbers.size());
        assertEquals(2, testNumbers.get(0));
        assertEquals(10, testNumbers.get(4));
    }

    @Test
    @DisplayName("Iterate with Iterator")
    void testIterateWithIterator() {
        int count = iterationFeatures.iterateWithIterator(strings);
        assertEquals(4, count);
    }

    @Test
    @DisplayName("Count using spliterator")
    void testCountUsingSpliterator() {
        long count = iterationFeatures.countUsingSpliterator(numbers);
        assertEquals(5, count);
    }

    @Test
    @DisplayName("Sum using forEach")
    void testSumUsingForEach() {
        int sum = iterationFeatures.sumUsingForEach(numbers);
        assertEquals(15, sum); // 1+2+3+4+5
    }

    @Test
    @DisplayName("Conditional forEach")
    void testConditionalForEach() {
        List<String> result = new ArrayList<>();
        iterationFeatures.conditionalForEach(
                strings,
                s -> s.length() > 5,
                result::add
        );

        assertEquals(2, result.size());
        assertTrue(result.contains("banana"));
        assertTrue(result.contains("cherry"));
    }

    @Test
    @DisplayName("forEach with empty list")
    void testForEachEmpty() {
        List<String> empty = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(0);
        iterationFeatures.forEachWithAction(empty, s -> count.incrementAndGet());
        assertEquals(0, count.get());
    }

    @Test
    @DisplayName("forEach with null in map")
    void testForEachMapWithNulls() {
        Map<String, Integer> mapWithNull = new HashMap<>();
        mapWithNull.put("one", 1);
        mapWithNull.put("two", null);

        List<Integer> values = new ArrayList<>();
        mapWithNull.forEach((k, v) -> {
            if (v != null) {
                values.add(v);
            }
        });

        assertEquals(1, values.size());
    }
}
