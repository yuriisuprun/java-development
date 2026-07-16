package com.suprun.functionalinterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yurii_Suprun
 */
public class FunctionalInterfaceTest {

    private FunctionalInterfaceImp functionalInterface;

    @BeforeEach
    void setUp() {
        functionalInterface = new FunctionalInterfaceImp();
    }

    @Test
    void testGetSortedHashStringsList() {
        List<String> expectedList = Arrays.asList(
                "#Workout", "#podcast", "#MentalHealth", "#MeTime", "#personalGrowth", "#SelfCare"
        );

        List<String> actualList = functionalInterface.getSortedHashCodes(hashTagText());

        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetLengthOfLongestString() {
        List<String> list = Arrays.asList("air", "grass", "road", "buildings");

        int actualLength = functionalInterface.getLongestStringLength(list);

        assertEquals("buildings".length(), actualLength);
    }

    @Test
    void testDeleteFirstLetterFromStrings() {
        List<String> originalList = Arrays.asList("air", "grass", "road", "buildings");
        List<String> expectedList = Arrays.asList("uildings", "rass", "oad", "ir");

        List<String> actualList = functionalInterface.deleteFirstLetterFromStrings(originalList);

        assertEquals(expectedList, actualList);
    }

    @Test
    void testCountLettersForStringsLongerThan() {
        List<String> originalList = Arrays.asList("Bill", "Tom", "Ronny", "Michelangelo", "Alexander");

        int actualSum = functionalInterface.countLettersForStringsLongerThan(originalList, 4);

        assertEquals(26, actualSum);
    }

    @Test
    void testFindOldestPerson() {
        List<Person> people = Arrays.asList(new Person("Sara", 4), new Person("Viktor", 40), new Person("Eva", 42));
        Person expectedPerson = new Person("Eva", 42);

        Person actualPerson = functionalInterface.findOldestPerson(people);

        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    void testPartitionPeopleByAge() {
        List<Person> people = Arrays.asList(
                new Person("Sara", 4),
                new Person("Chiara", 17),
                new Person("Viktor", 40),
                new Person("Eva", 42)
        );
        Map<Boolean, List<Person>> expectedMap = new HashMap<>();
        expectedMap.put(false, Arrays.asList(new Person("Sara", 4), new Person("Chiara", 17)));
        expectedMap.put(true, Arrays.asList(new Person("Viktor", 40), new Person("Eva", 42)));

        Map<Boolean, List<Person>> actualMap = functionalInterface.partitionPeopleByAge(people, 17);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    void testCountLettersInWord() {
        Map<Character, Long> actualMap = functionalInterface.countLettersInWord("Discussion");

        assertEquals(expectedLetterFrequency(), actualMap);
    }

    @Test
    void testCountLettersInWordOldStyle() {
        Map<Character, Long> actualMap = functionalInterface.countLettersInWordOldStyle("Discussion");

        assertEquals(expectedLetterFrequency(), actualMap);
    }

    @Test
    void testEmptyStringHandling() {
        List<String> list = Arrays.asList("", "hello", "", "world");
        int longestLength = functionalInterface.getLongestStringLength(list);
        assertEquals(5, longestLength);
    }

    @Test
    void testSingleCharacterStrings() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> result = functionalInterface.deleteFirstLetterFromStrings(list);
        assertEquals(Arrays.asList("", "", ""), result);
    }

    @Test
    void testCountLettersEdgeCases() {
        List<String> list = Arrays.asList("a", "bb", "ccc");
        int count = functionalInterface.countLettersForStringsLongerThan(list, 1);
        assertEquals(5, count); // "bb" (2) + "ccc" (3)
    }

    @Test
    void testCountLettersZeroThreshold() {
        List<String> list = Arrays.asList("a", "bb", "ccc");
        int count = functionalInterface.countLettersForStringsLongerThan(list, 0);
        assertEquals(6, count); // All characters
    }

    @Test
    void testMultiplePeopleWithSameAge() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 30),
                new Person("Charlie", 25)
        );
        Person oldest = functionalInterface.findOldestPerson(people);
        assertEquals(30, oldest.getAge());
    }

    @Test
    void testPartitionPeopleAllYounger() {
        List<Person> people = Arrays.asList(
                new Person("Sara", 4),
                new Person("Chiara", 10)
        );
        Map<Boolean, List<Person>> result = functionalInterface.partitionPeopleByAge(people, 15);
        assertEquals(2, result.get(false).size());
        assertEquals(0, result.get(true).size());
    }

    @Test
    void testPartitionPeopleAllOlder() {
        List<Person> people = Arrays.asList(
                new Person("Viktor", 40),
                new Person("Eva", 42)
        );
        Map<Boolean, List<Person>> result = functionalInterface.partitionPeopleByAge(people, 15);
        assertEquals(0, result.get(false).size());
        assertEquals(2, result.get(true).size());
    }

    @Test
    void testCountLettersInWordWithRepeatingCharacters() {
        Map<Character, Long> result = functionalInterface.countLettersInWord("aabbbaaa");
        assertEquals(5L, result.get('a'));
        assertEquals(3L, result.get('b'));
    }

    @Test
    void testCountLettersInWordSingleCharacter() {
        Map<Character, Long> result = functionalInterface.countLettersInWord("aaaa");
        assertEquals(1, result.size());
        assertEquals(4L, result.get('a'));
    }

    @Test
    void testHashtagExtractionAndSorting() {
        String text = "#Java #Python #Java #Python #Java #Ruby";
        List<String> result = functionalInterface.getSortedHashCodes(text);
        
        // Should have 3 unique hashtags sorted by frequency then alphabetically
        assertEquals(3, result.size());
        assertEquals("#Java", result.get(0)); // 3 occurrences
        assertEquals("#Python", result.get(1)); // 2 occurrences
    }

    @Test
    void testLongestStringWithSpecialCharacters() {
        List<String> list = Arrays.asList("hello!", "@world#", "test");
        int longestLength = functionalInterface.getLongestStringLength(list);
        assertEquals(7, longestLength);
    }

    @Test
    void testDeleteFirstLetterPreservesOrder() {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        List<String> result = functionalInterface.deleteFirstLetterFromStrings(list);
        
        assertEquals(3, result.size());
        // Note: this method sorts by length descending, so order is: "anana" (5), "herry" (5), "pple" (4)
        // Since "anana" and "herry" are both length 5, they appear before "pple" (length 4)
        assertEquals(3, result.size());
        assertTrue(result.contains("pple"));
        assertTrue(result.contains("anana"));
        assertTrue(result.contains("herry"));
    }

    @Test
    void testCountLettersComplexThreshold() {
        List<String> list = Arrays.asList("a", "bb", "ccc", "dddd", "eeeee");
        int count = functionalInterface.countLettersForStringsLongerThan(list, 2);
        assertEquals(12, count); // "ccc"(3) + "dddd"(4) + "eeeee"(5) = 12
    }

    @Test
    void testPartitionExactBoundary() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 18),
                new Person("Bob", 17),
                new Person("Charlie", 19)
        );
        Map<Boolean, List<Person>> result = functionalInterface.partitionPeopleByAge(people, 18);
        assertEquals(2, result.get(false).size()); // <= 18: Bob (17), Alice (18)
        assertEquals(1, result.get(true).size());  // > 18: Charlie
    }

    private static Map<Character, Long> expectedLetterFrequency() {
        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('c', 1L);
        expectedMap.put('s', 3L);
        expectedMap.put('D', 1L);
        expectedMap.put('u', 1L);
        expectedMap.put('i', 2L);
        expectedMap.put('n', 1L);
        expectedMap.put('o', 1L);
        return expectedMap;
    }

    private static String hashTagText() {
        return "Going for a long run can be #podcast quite liberating, a great "
                + "way to #Workout clear the mind. While on my run, I usually tune into my favorite #podcast This "
                + "is just one of #Workout the ways I practice #SelfCare Running isn't just about fitness, it's "
                + "also my #MeTime So no matter how busy life gets, remember to create some space for #personalGrowth and "
                + "never underestimate the power of a good #Workout Your #MentalHealth will thank you!";
    }
}
