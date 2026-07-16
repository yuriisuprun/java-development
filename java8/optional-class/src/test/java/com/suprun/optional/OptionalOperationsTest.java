package com.suprun.optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class OptionalOperationsTest {

    private OptionalOperations optionalOperations;

    @BeforeEach
    void setUp() {
        optionalOperations = new OptionalOperations();
    }

    @Test
    void testWrapValueWithNonNull() {
        Optional<String> result = optionalOperations.wrapValue("test");
        assertTrue(result.isPresent());
        assertEquals("test", result.get());
    }

    @Test
    void testWrapValueWithNull() {
        Optional<String> result = optionalOperations.wrapValue(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetOrDefault() {
        Optional<String> presentOptional = Optional.of("value");
        String result = optionalOperations.getOrDefault(presentOptional, "default");
        assertEquals("value", result);

        Optional<String> emptyOptional = Optional.empty();
        result = optionalOperations.getOrDefault(emptyOptional, "default");
        assertEquals("default", result);
    }

    @Test
    void testGetOrSupply() {
        Optional<String> presentOptional = Optional.of("value");
        String result = optionalOperations.getOrSupply(presentOptional, () -> "supplied");
        assertEquals("value", result);

        Optional<String> emptyOptional = Optional.empty();
        result = optionalOperations.getOrSupply(emptyOptional, () -> "supplied");
        assertEquals("supplied", result);
    }

    @Test
    void testTransformValue() {
        Optional<String> optional = Optional.of("hello");
        Optional<Integer> result = optionalOperations.transformValue(optional, String::length);
        assertTrue(result.isPresent());
        assertEquals(5, result.get());

        Optional<String> emptyOptional = Optional.empty();
        Optional<Integer> emptyResult = optionalOperations.transformValue(emptyOptional, String::length);
        assertTrue(emptyResult.isEmpty());
    }

    @Test
    void testChainOperations() {
        Optional<String> optional = Optional.of("5");
        Optional<Integer> result = optionalOperations.chainOperations(optional, s -> {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        });
        assertTrue(result.isPresent());
        assertEquals(5, result.get());

        Optional<String> invalidOptional = Optional.of("abc");
        Optional<Integer> emptyResult = optionalOperations.chainOperations(invalidOptional, s -> {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        });
        assertTrue(emptyResult.isEmpty());
    }

    @Test
    void testFilterValue() {
        Optional<Integer> optional = Optional.of(10);
        Optional<Integer> result = optionalOperations.filterValue(optional, n -> n > 5);
        assertTrue(result.isPresent());
        assertEquals(10, result.get());

        Optional<Integer> filteredOutResult = optionalOperations.filterValue(optional, n -> n > 15);
        assertTrue(filteredOutResult.isEmpty());

        Optional<Integer> emptyOptional = Optional.empty();
        Optional<Integer> emptyResult = optionalOperations.filterValue(emptyOptional, n -> n > 5);
        assertTrue(emptyResult.isEmpty());
    }

    @Test
    void testIfPresentAction() {
        AtomicBoolean actionExecuted = new AtomicBoolean(false);
        Optional<String> optional = Optional.of("value");
        optionalOperations.ifPresentAction(optional, s -> actionExecuted.set(true));
        assertTrue(actionExecuted.get());

        AtomicBoolean emptyActionExecuted = new AtomicBoolean(false);
        Optional<String> emptyOptional = Optional.empty();
        optionalOperations.ifPresentAction(emptyOptional, s -> emptyActionExecuted.set(true));
        assertFalse(emptyActionExecuted.get());
    }

    @Test
    void testIfPresentOrElse() {
        AtomicBoolean presentExecuted = new AtomicBoolean(false);
        AtomicBoolean emptyExecuted = new AtomicBoolean(false);

        Optional<String> optional = Optional.of("value");
        optionalOperations.ifPresentOrElse(optional, s -> presentExecuted.set(true), () -> emptyExecuted.set(true));
        assertTrue(presentExecuted.get());
        assertFalse(emptyExecuted.get());

        presentExecuted.set(false);
        Optional<String> emptyOptional = Optional.empty();
        optionalOperations.ifPresentOrElse(emptyOptional, s -> presentExecuted.set(true), () -> emptyExecuted.set(true));
        assertFalse(presentExecuted.get());
        assertTrue(emptyExecuted.get());
    }

    @Test
    void testFindUserByName() {
        List<OptionalOperations.User> users = Arrays.asList(
                new OptionalOperations.User("Alice", Optional.of("alice@example.com"), Optional.of(25)),
                new OptionalOperations.User("Bob", Optional.of("bob@example.com"), Optional.of(30)),
                new OptionalOperations.User("Charlie", Optional.empty(), Optional.of(35))
        );

        Optional<OptionalOperations.User> result = optionalOperations.findUserByName(users, "Alice");
        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getName());

        Optional<OptionalOperations.User> notFound = optionalOperations.findUserByName(users, "David");
        assertTrue(notFound.isEmpty());

        Optional<OptionalOperations.User> caseInsensitive = optionalOperations.findUserByName(users, "BOB");
        assertTrue(caseInsensitive.isPresent());
        assertEquals("Bob", caseInsensitive.get().getName());
    }

    @Test
    void testGetUserEmailOrDefault() {
        List<OptionalOperations.User> users = Arrays.asList(
                new OptionalOperations.User("Alice", Optional.of("alice@example.com"), Optional.of(25)),
                new OptionalOperations.User("Charlie", Optional.empty(), Optional.of(35))
        );

        String aliceEmail = optionalOperations.getUserEmailOrDefault(users, "Alice");
        assertEquals("alice@example.com", aliceEmail);

        String defaultEmail = optionalOperations.getUserEmailOrDefault(users, "David");
        assertEquals("unknown@example.com", defaultEmail);

        String emptyEmail = optionalOperations.getUserEmailOrDefault(users, "Charlie");
        assertEquals("unknown@example.com", emptyEmail);
    }

    @Test
    void testGetAllUserEmails() {
        List<OptionalOperations.User> users = Arrays.asList(
                new OptionalOperations.User("Alice", Optional.of("alice@example.com"), Optional.of(25)),
                new OptionalOperations.User("Bob", Optional.of("bob@example.com"), Optional.of(30)),
                new OptionalOperations.User("Charlie", Optional.empty(), Optional.of(35))
        );

        List<String> emails = optionalOperations.getAllUserEmails(users);
        assertEquals(2, emails.size());
        assertTrue(emails.contains("alice@example.com"));
        assertTrue(emails.contains("bob@example.com"));
    }

    @Test
    void testCombineOptionals() {
        Optional<String> opt1 = Optional.of("Hello");
        Optional<String> opt2 = Optional.of("World");
        Optional<String> result = optionalOperations.combineOptionals(opt1, opt2);
        assertTrue(result.isPresent());
        assertEquals("Hello World", result.get());

        Optional<String> emptyOpt = Optional.empty();
        Optional<String> emptyResult = optionalOperations.combineOptionals(opt1, emptyOpt);
        assertTrue(emptyResult.isEmpty());

        Optional<String> otherEmptyResult = optionalOperations.combineOptionals(emptyOpt, opt2);
        assertTrue(otherEmptyResult.isEmpty());
    }

    @Test
    void testOrElseThrowWithValidValue() {
        Optional<Integer> optional = Optional.of(42);
        int result = optional.orElseThrow(() -> new IllegalArgumentException("Value not found"));
        assertEquals(42, result);
    }

    @Test
    void testOrElseThrowWithEmptyValue() {
        Optional<Integer> optional = Optional.empty();
        assertThrows(IllegalArgumentException.class,
                () -> optional.orElseThrow(() -> new IllegalArgumentException("Value not found")));
    }

    @Test
    void testMultipleFiltersChained() {
        Optional<String> optional = Optional.of("HelloWorld");
        Optional<String> result = optional
                .filter(s -> s.length() > 5)
                .filter(s -> s.contains("World"))
                .filter(s -> !s.isEmpty());

        assertTrue(result.isPresent());
        assertEquals("HelloWorld", result.get());
    }

    @Test
    void testMapChained() {
        Optional<String> optional = Optional.of("123");
        Optional<Integer> result = optional
                .map(Integer::parseInt)
                .map(n -> n * 2);

        assertTrue(result.isPresent());
        assertEquals(246, result.get());
    }

    @Test
    void testFlatMapChained() {
        Optional<String> optional = Optional.of("5");
        Optional<Integer> result = optional
                .flatMap(s -> {
                    try {
                        return Optional.of(Integer.parseInt(s) * 10);
                    } catch (NumberFormatException e) {
                        return Optional.empty();
                    }
                })
                .flatMap(n -> Optional.of(n + 5));

        assertTrue(result.isPresent());
        assertEquals(55, result.get());
    }

    @Test
    void testOrElseChainedOptionals() {
        Optional<String> opt1 = Optional.empty();
        Optional<String> opt2 = Optional.empty();
        Optional<String> opt3 = Optional.of("found");

        String result = opt1.or(() -> opt2).or(() -> opt3).orElse("default");
        assertEquals("found", result);
    }

    @Test
    void testStreamFromOptional() {
        Optional<String> optional = Optional.of("test");
        List<String> result = optional.stream().collect(java.util.stream.Collectors.toList());

        assertEquals(1, result.size());
        assertEquals("test", result.get(0));
    }

    @Test
    void testStreamFromOptionalEmpty() {
        Optional<String> optional = Optional.empty();
        List<String> result = optional.stream().collect(java.util.stream.Collectors.toList());

        assertEquals(0, result.size());
    }

    @Test
    void testComplexOptionalUserScenario() {
        List<OptionalOperations.User> users = Arrays.asList(
                new OptionalOperations.User("Alice", Optional.of("alice@example.com"), Optional.of(25)),
                new OptionalOperations.User("Bob", Optional.of("bob@example.com"), Optional.of(17)),
                new OptionalOperations.User("Charlie", Optional.empty(), Optional.of(35))
        );

        String result = optionalOperations.findUserByName(users, "Bob")
                .filter(u -> u.getAge().map(age -> age >= 18).orElse(false))
                .flatMap(OptionalOperations.User::getEmail)
                .orElse("user is not an adult");

        assertEquals("user is not an adult", result);
    }

    @Test
    void testOptionalOfNullableWithStream() {
        List<String> values = Arrays.asList("apple", null, "banana", null, "cherry");
        List<String> result = values.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(java.util.stream.Collectors.toList());

        assertEquals(3, result.size());
        assertEquals(Arrays.asList("apple", "banana", "cherry"), result);
    }

    @Test
    void testOptionalWithPredicate() {
        Optional<Integer> optional = Optional.of(15);
        Optional<Integer> evenResult = optional.filter(n -> n % 2 == 0);
        Optional<Integer> oddResult = optional.filter(n -> n % 2 != 0);

        assertTrue(evenResult.isEmpty());
        assertTrue(oddResult.isPresent());
        assertEquals(15, oddResult.get());
    }

    @Test
    void testOptionalCompositionWithMultipleTransformations() {
        Optional<String> result = Optional.of(100)
                .map(n -> n * 2)
                .map(n -> n + 50)
                .map(Object::toString);

        assertTrue(result.isPresent());
        assertEquals("250", result.get());
    }
}
