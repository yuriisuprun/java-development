package com.suprun.functionalinterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
