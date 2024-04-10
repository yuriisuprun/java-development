package com.suprun.streamapi;

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
 * A test class for {@link StreamApi}
 *
 * @author Yurii_Suprun
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreamApiTest {

    private StreamApi streamApi;

    @BeforeEach
    void setUp() {
        streamApi = new StreamApi();
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
        List<String> actualList = streamApi.getSortedHashCodes(hashTagText);

        assertEquals(expectedList, actualList);
    }

    @Test
    @Order(2)
    void testGetLengthOfLongestString() {
        List<String> list = List.of("air", "grass", "road", "buildings");
        int expectedLength = list.get(3).length();
        int actualLength = streamApi.getLongestStringLength(list);

        assertEquals(expectedLength, actualLength);
    }

    @Test
    @Order(3)
    void testDeleteFirstLetterFromStrings() {
        List<String> originalList = List.of("air", "grass", "road", "buildings");
        List<String> expectedList = List.of("uildings", "rass", "oad", "ir");
        List<String> actualList = streamApi.deleteFirstLetterFromStrings(originalList);

        assertEquals(expectedList, actualList);
    }

    @Test
    @Order(4)
    void testCountLettersForStringsLongerThan() {
        List<String> originalList = List.of("Bill", "Tom", "Ronny", "Michelangelo", "Alexander");
        int actualSum = streamApi.countLettersForStringsLongerThan(originalList, 4);

        assertEquals(26, actualSum);
    }

    @Test
    @Order(5)
    void testFindOldestPerson() {
        List<Person> people = List.of(new Person("Sara", 4), new Person("Viktor".intern(), 40), new Person("Eva", 42));
        Person expectedPerson = new Person("Eva", 42);
        Person actualPerson = streamApi.findOldestPerson(people);

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
        Map<Boolean, List<Person>> actualMap = streamApi.partitionPeopleByAge(people);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    @Order(7)
    void testCountLettersInWord() {
        String String = "Discussion";
        Map<Character, Long> expectedMap = Map.of('c',1L, 's', 3L, 'D', 1L, 'u',1L, 'i', 2L, 'n', 1L, 'o', 1L);
        Map<Character, Long> actualMap = streamApi.countLettersInWord(String);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    @Order(7)
    void testCountLettersInWordOldStyle() {
        String String = "Discussion";
        Map<Character, Long> expectedMap = Map.of('c',1L, 's', 3L, 'D', 1L, 'u',1L, 'i', 2L, 'n', 1L, 'o', 1L);
        Map<Character, Long> actualMap = streamApi.countLettersInWordOldStyle(String);

        assertEquals(expectedMap, actualMap);
    }
//
//    private Map<Boolean, List<Account>> getExpectedMaleMap() {
//        Map<Boolean, List<Account>> expectedMap = new HashMap<>(2);
//        expectedMap.put(Boolean.TRUE, Arrays.asList(accounts.get(0), accounts.get(2), accounts.get(3)));
//        expectedMap.put(Boolean.FALSE, Arrays.asList(accounts.get(1)));
//        return expectedMap;
//    }
//
//    private List<Account> getExpectedList() {
//        return Arrays.asList(accounts.get(0), accounts.get(2));
//    }
//
//    @Test
//    @Order(4)
//    void groupAccountsByEmailDomain() {
//        Map<String, List<Account>> expectedEmailMap = getExpectedEmailMap();
//        Map<String, List<Account>> emailDomainToAccountsMap = streams.groupAccountsByEmailDomain();
//
//        assertEquals(expectedEmailMap, emailDomainToAccountsMap);
//    }
//
//    private Map<String, List<Account>> getExpectedEmailMap() {
//        Map<String, List<Account>> expectedEmailMap = new HashMap<>();
//        expectedEmailMap.put("gmail.com", Arrays.asList(accounts.get(0), accounts.get(2)));
//        expectedEmailMap.put("mail.com", Arrays.asList(accounts.get(1)));
//        expectedEmailMap.put("yahoo.com", Arrays.asList(accounts.get(3)));
//
//        return expectedEmailMap;
//    }
//
//    @Test
//    @Order(5)
//    void getNumOfLettersInFirstAndLastNames() {
//        int numOfLettersInFirstAndLastNames = streams.getNumOfLettersInFirstAndLastNames();
//
//        assertEquals(47, numOfLettersInFirstAndLastNames);
//    }
//
//    @Test
//    @Order(6)
//    void calculateTotalBalance() {
//        BigDecimal totalBalance = streams.calculateTotalBalance();
//
//        assertEquals(BigDecimal.valueOf(241864), totalBalance);
//    }
//
//
//    @Test
//    @Order(7)
//    void sortByFirstAndLastNames() {
//        List<Account> sortedList = streams.sortByFirstAndLastNames();
//
//        assertEquals(1L, sortedList.get(0).getId().longValue());
//        assertEquals(4L, sortedList.get(1).getId().longValue());
//        assertEquals(3L, sortedList.get(2).getId().longValue());
//        assertEquals(2L, sortedList.get(3).getId().longValue());
//
//    }
//
//    @Test
//    @Order(8)
//    void containsAccountWithEmailDomain() {
//        assertTrue(streams.containsAccountWithEmailDomain("gmail.com"));
//        assertTrue(streams.containsAccountWithEmailDomain("yahoo.com"));
//        assertFalse(streams.containsAccountWithEmailDomain("ukr.net"));
//    }
//
//    @Test
//    @Order(9)
//    void getBalanceByEmail() {
//        Account account = accounts.get(1);
//        BigDecimal balance = streams.getBalanceByEmail(account.getEmail());
//
//        assertEquals(account.getBalance(), balance);
//    }
//
//    @Test
//    @Order(10)
//    void getBalanceByEmailThrowsException() {
//        String fakeEmail = "fake@mail.com";
//        try {
//            streams.getBalanceByEmail(fakeEmail);
//            fail("Should throw exception");
//        } catch (Exception e) {
//            assertTrue(e instanceof EntityNotFoundException);
//            assertEquals(String.format("Cannot find Account by email=%s", fakeEmail), e.getMessage());
//        }
//    }
//
//    @Test
//    @Order(11)
//    void collectAccountsById() {
//        Map<Long, Account> idToAccountMap = streams.collectAccountsById();
//
//        assertEquals(accounts.get(0), idToAccountMap.get(1L));
//        assertEquals(accounts.get(1), idToAccountMap.get(2L));
//        assertEquals(accounts.get(2), idToAccountMap.get(3L));
//        assertEquals(accounts.get(3), idToAccountMap.get(4L));
//    }
//
//    @Test
//    @Order(12)
//    void collectBalancesByEmailForAccountsCreatedOn() {
//        Account account = accounts.get(3);
//
//        Map<String, BigDecimal> emailToBalanceMap = streams.collectBalancesByEmailForAccountsCreatedOn(account.getCreationDate().getYear());
//
//        assertEquals(Map.of(account.getEmail(), account.getBalance()), emailToBalanceMap);
//    }
//
//    @Test
//    @Order(13)
//    void groupFirstNamesByLastNames() {
//        Map<String, Set<String>> lastToFirstNamesMap = streams.groupFirstNamesByLastNames();
//
//        assertEquals(4, lastToFirstNamesMap.size());
//        assertEquals(Set.of("Justin"), lastToFirstNamesMap.get("Butler"));
//        assertEquals(Set.of("Olivia"), lastToFirstNamesMap.get("Cardenas"));
//        assertEquals(Set.of("Nolan"), lastToFirstNamesMap.get("Donovan"));
//        assertEquals(Set.of("Lucas"), lastToFirstNamesMap.get("Lynn"));
//    }
//
//    @Test
//    @Order(14)
//    void groupCommaSeparatedFirstNamesByBirthdayMonth() {
//        Map<Month, String> birthdayMonthToFirstNamesMap = streams.groupCommaSeparatedFirstNamesByBirthdayMonth();
//
//        assertEquals(3, birthdayMonthToFirstNamesMap.size());
//        assertEquals("Olivia", birthdayMonthToFirstNamesMap.get(Month.JANUARY));
//        assertEquals("Justin, Nolan", birthdayMonthToFirstNamesMap.get(Month.APRIL));
//        assertEquals("Lucas", birthdayMonthToFirstNamesMap.get(Month.MAY));
//    }
//
//    @Test
//    @Order(15)
//    void groupTotalBalanceByCreationMonth() {
//        Map<Month, BigDecimal> totalBalanceByAccountCreationMonth = streams.groupTotalBalanceByCreationMonth();
//
//        assertEquals(2, totalBalanceByAccountCreationMonth.size());
//        assertEquals(BigDecimal.valueOf(210995), totalBalanceByAccountCreationMonth.get(Month.JUNE));
//        assertEquals(BigDecimal.valueOf(30869), totalBalanceByAccountCreationMonth.get(Month.MARCH));
//    }
//
//    @Test
//    @Order(16)
//    void getCharacterFrequencyInFirstNames() {
//        Map<Character, Long> characterFrequencyInFirstAndLastNames = streams.getCharacterFrequencyInFirstNames();
//
//        assertEquals(3, characterFrequencyInFirstAndLastNames.get('a').longValue());
//        assertEquals(1, characterFrequencyInFirstAndLastNames.get('c').longValue());
//        assertEquals(3, characterFrequencyInFirstAndLastNames.get('i').longValue());
//        assertEquals(1, characterFrequencyInFirstAndLastNames.get('J').longValue());
//        assertEquals(1, characterFrequencyInFirstAndLastNames.get('L').longValue());
//        assertEquals(2, characterFrequencyInFirstAndLastNames.get('l').longValue());
//        assertEquals(2, characterFrequencyInFirstAndLastNames.get('u').longValue());
//    }
//
//    @MethodSource("getCharacterFrequencyIgnoreCaseInFirstAndLastNamesArgs")
//    @ParameterizedTest
//    @Order(17)
//    void getCharacterFrequencyIgnoreCaseInFirstAndLastNames(int nameLengthBound, Map<Character, Long> resultMap) {
//        var characterFrequencyInFirstAndLastNames = streams.getCharacterFrequencyIgnoreCaseInFirstAndLastNames(nameLengthBound);
//
//        assertThat(characterFrequencyInFirstAndLastNames).isEqualTo(resultMap);
//    }
//
//    private static Stream<Arguments> getCharacterFrequencyIgnoreCaseInFirstAndLastNamesArgs() {
//        return Stream.of(
//                Arguments.arguments(2, buildMap(accounts, 2)),
//                Arguments.arguments(5, buildMap(accounts, 5)),
//                Arguments.arguments(7, buildMap(accounts, 7))
//        );
//    }
//
//    private static Map<Character, Long> buildMap(List<Account> accounts, int nameLengthBound) {
//        var resultMap = new HashMap<Character, Long>();
//        for (var a : accounts) {
//            processName(resultMap, a.getFirstName(), nameLengthBound);
//            processName(resultMap, a.getLastName(), nameLengthBound);
//        }
//        return resultMap;
//    }
//
//    private static void processName(Map<Character, Long> resultMap, String name, int nameLengthBound) {
//        if (name.length() >= nameLengthBound) {
//            var chars = name.toLowerCase().toCharArray();
//            for (Character c : chars) {
//                if (resultMap.putIfAbsent(c, 1L) != null) {
//                    resultMap.compute(c, (k, counter) -> counter + 1L);
//                }
//            }
//        }
//    }
}


