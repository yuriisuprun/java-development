package com.suprun.functionalinterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Yurii_Suprun
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FunctionalInterfaceTest {

    private FunctionalInterfaceImp functionalInterface;

    @BeforeEach
    void setUp() {
        functionalInterface = new FunctionalInterfaceImp();
    }

    @Test
    @Order(1)
    void testGetSortedHashStringsList() {
        String hashTagText = """
            Going for a long run can be #podcast quite liberating, a great
            way to #Workout clear the mind. While on my run, I usually tune into my favorite #podcast This
            is just one of #Workout the ways I practice #SelfCare Running isn't just about fitness, it's
            also my #MeTime So no matter how busy life gets, remember to create some space for #personalGrowth and
            never underestimate the power of a good #Workout Your #MentalHealth will thank you!""";

        List<String> expectedList = List.of("#Workout", "#podcast", "#MentalHealth", "#SelfCare", "#personalGrowth", "#MeTime");
        List<String> actualList = functionalInterface.getSortedHashCodes(hashTagText);

        assertEquals(expectedList, actualList);
    }

    @Test
    @Order(2)
    void testGetLengthOfLongestString() {
        List<String> list = List.of("air", "grass", "road", "buildings");
        int expectedLength = list.get(3).length();
        int actualLength = functionalInterface.getLongestStringLength(list);

        assertEquals(expectedLength, actualLength);
    }

    @Test
    @Order(3)
    void testDeleteFirstLetterFromStrings() {
        List<String> originalList = List.of("air", "grass", "road", "buildings");
        List<String> expectedList = List.of("uildings", "rass", "oad", "ir");
        List<String> actualList = functionalInterface.deleteFirstLetterFromStrings(originalList);

        assertEquals(expectedList, actualList);
    }

    @Test
    @Order(4)
    void testCountLettersForStringsLongerThan() {
        List<String> originalList = List.of("Bill", "Tom", "Ronny", "Michelangelo", "Alexander");
        int actualSum = functionalInterface.countLettersForStringsLongerThan(originalList, 4);

        assertEquals(26, actualSum);
    }

    @Test
    @Order(5)
    void testFindOldestPerson() {
        List<Person> people = List.of(new Person("Sara", 4), new Person("Viktor".intern(), 40), new Person("Eva", 42));
        Person expectedPerson = new Person("Eva", 42);
        Person actualPerson = functionalInterface.findOldestPerson(people);

        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    @Order(6)
    void testPartitionPeopleByAge() {
        List<Person> people = List.of(new Person("Sara", 4), new Person("Chiara", 17),
                new Person("Viktor".intern(), 40), new Person("Eva", 42));
        Map<Boolean, List<Person>> expectedMap = new HashMap<>();
        expectedMap.put(false, List.of(new Person("Sara", 4), new Person("Chiara", 17)));
        expectedMap.put(true, List.of(new Person("Viktor".intern(), 40), new Person("Eva", 42)));
        Map<Boolean, List<Person>> actualMap = functionalInterface.partitionPeopleByAge(people, 17);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    @Order(7)
    void testCountLettersInWord() {
        String String = "Discussion";
        Map<Character, Long> expectedMap = Map.of('c',1L, 's', 3L, 'D', 1L, 'u',1L, 'i', 2L, 'n', 1L, 'o', 1L);
        Map<Character, Long> actualMap = functionalInterface.countLettersInWord(String);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    @Order(7)
    void testCountLettersInWordOldStyle() {
        String String = "Discussion";
        Map<Character, Long> expectedMap = Map.of('c',1L, 's', 3L, 'D', 1L, 'u',1L, 'i', 2L, 'n', 1L, 'o', 1L);
        Map<Character, Long> actualMap = functionalInterface.countLettersInWordOldStyle(String);

        assertEquals(expectedMap, actualMap);
    }
}


