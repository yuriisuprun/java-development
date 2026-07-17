package com.suprun.coding;

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

    // ==================== Exception Handling Tests ====================

    @Test
    void testTryOperationSuccess() {
        Optional<String> result = optionalOperations.tryOperation(() -> "success");
        assertTrue(result.isPresent());
        assertEquals("success", result.get());
    }

    @Test
    void testTryOperationException() {
        Optional<Integer> result = optionalOperations.tryOperation(() -> {
            throw new RuntimeException("test error");
        });
        assertTrue(result.isEmpty());
    }

    @Test
    void testSafeParseInt() {
        Optional<Integer> validResult = optionalOperations.safeParseInt("42");
        assertTrue(validResult.isPresent());
        assertEquals(42, validResult.get());

        Optional<Integer> invalidResult = optionalOperations.safeParseInt("not a number");
        assertTrue(invalidResult.isEmpty());

        Optional<Integer> negativeResult = optionalOperations.safeParseInt("-123");
        assertTrue(negativeResult.isPresent());
        assertEquals(-123, negativeResult.get());
    }

    @Test
    void testSafeParseDouble() {
        Optional<Double> validResult = optionalOperations.safeParseDouble("3.14");
        assertTrue(validResult.isPresent());
        assertEquals(3.14, validResult.get());

        Optional<Double> invalidResult = optionalOperations.safeParseDouble("invalid");
        assertTrue(invalidResult.isEmpty());

        Optional<Double> scientificResult = optionalOperations.safeParseDouble("1.5E2");
        assertTrue(scientificResult.isPresent());
        assertEquals(150.0, scientificResult.get());
    }

    // ==================== Multiple Optional Combinations Tests ====================

    @Test
    void testCombineThree() {
        Optional<String> opt1 = Optional.of("A");
        Optional<String> opt2 = Optional.of("B");
        Optional<String> opt3 = Optional.of("C");

        Optional<String> result = optionalOperations.combineThree(
                opt1, opt2, opt3,
                (a, b, c) -> a + b + c
        );
        assertTrue(result.isPresent());
        assertEquals("ABC", result.get());
    }

    @Test
    void testCombineThreeWithEmpty() {
        Optional<String> opt1 = Optional.of("A");
        Optional<String> opt2 = Optional.empty();
        Optional<String> opt3 = Optional.of("C");

        Optional<String> result = optionalOperations.combineThree(
                opt1, opt2, opt3,
                (a, b, c) -> a + b + c
        );
        assertTrue(result.isEmpty());
    }

    @Test
    void testCombineFour() {
        Optional<Integer> opt1 = Optional.of(1);
        Optional<Integer> opt2 = Optional.of(2);
        Optional<Integer> opt3 = Optional.of(3);
        Optional<Integer> opt4 = Optional.of(4);

        Optional<Integer> result = optionalOperations.combineFour(
                opt1, opt2, opt3, opt4,
                (a, b, c, d) -> a + b + c + d
        );
        assertTrue(result.isPresent());
        assertEquals(10, result.get());
    }

    @Test
    void testCombineFourWithOneEmpty() {
        Optional<Integer> opt1 = Optional.of(1);
        Optional<Integer> opt2 = Optional.of(2);
        Optional<Integer> opt3 = Optional.empty();
        Optional<Integer> opt4 = Optional.of(4);

        Optional<Integer> result = optionalOperations.combineFour(
                opt1, opt2, opt3, opt4,
                (a, b, c, d) -> a + b + c + d
        );
        assertTrue(result.isEmpty());
    }

    // ==================== Conditional Chaining Tests ====================

    @Test
    void testFilterAllPass() {
        Optional<Integer> optional = Optional.of(15);
        Optional<Integer> result = optionalOperations.filterAll(
                optional,
                n -> n > 10,
                n -> n < 20,
                n -> n % 5 == 0
        );
        assertTrue(result.isPresent());
        assertEquals(15, result.get());
    }

    @Test
    void testFilterAllFail() {
        Optional<Integer> optional = Optional.of(15);
        Optional<Integer> result = optionalOperations.filterAll(
                optional,
                n -> n > 10,
                n -> n < 20,
                n -> n % 2 == 0  // This fails for odd number 15
        );
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterAllEmpty() {
        Optional<Integer> optional = Optional.empty();
        Optional<Integer> result = optionalOperations.filterAll(
                optional,
                n -> n > 10,
                n -> n < 20
        );
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterAnyOneMatches() {
        Optional<Integer> optional = Optional.of(5);
        Optional<Integer> result = optionalOperations.filterAny(
                optional,
                n -> n > 10,
                n -> n == 5,
                n -> n < 0
        );
        assertTrue(result.isPresent());
        assertEquals(5, result.get());
    }

    @Test
    void testFilterAnyNoneMatch() {
        Optional<Integer> optional = Optional.of(5);
        Optional<Integer> result = optionalOperations.filterAny(
                optional,
                n -> n > 10,
                n -> n < 0,
                n -> n == 100
        );
        assertTrue(result.isEmpty());
    }

    // ==================== Validation Chain Tests ====================

    @Test
    void testValidateUserValid() {
        OptionalOperations.User validUser = new OptionalOperations.User(
                "John",
                Optional.of("john@example.com"),
                Optional.of(30)
        );
        Optional<String> error = optionalOperations.validateUser(validUser);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateUserEmptyName() {
        OptionalOperations.User invalidUser = new OptionalOperations.User(
                "",
                Optional.of("test@example.com"),
                Optional.of(30)
        );
        Optional<String> error = optionalOperations.validateUser(invalidUser);
        assertTrue(error.isPresent());
        assertTrue(error.get().contains("name"));
    }

    @Test
    void testValidateUserNegativeAge() {
        OptionalOperations.User invalidUser = new OptionalOperations.User(
                "John",
                Optional.of("john@example.com"),
                Optional.of(-5)
        );
        Optional<String> error = optionalOperations.validateUser(invalidUser);
        assertTrue(error.isPresent());
        assertTrue(error.get().contains("age"));
    }

    @Test
    void testValidateUserInvalidEmail() {
        OptionalOperations.User invalidUser = new OptionalOperations.User(
                "John",
                Optional.of("invalid-email"),
                Optional.of(30)
        );
        Optional<String> error = optionalOperations.validateUser(invalidUser);
        assertTrue(error.isPresent());
        assertTrue(error.get().contains("email"));
    }

    @Test
    void testValidateAllUsers() {
        List<OptionalOperations.User> users = Arrays.asList(
                new OptionalOperations.User("Alice", Optional.of("alice@example.com"), Optional.of(25)),
                new OptionalOperations.User("", Optional.of("bob@example.com"), Optional.of(30)),
                new OptionalOperations.User("Charlie", Optional.of("invalid"), Optional.of(-5))
        );

        List<String> errors = optionalOperations.validateAllUsers(users);
        assertEquals(2, errors.size());  // Two invalid users
    }

    // ==================== Optional to Exception Tests ====================

    @Test
    void testOrElseThrow() {
        Optional<String> optional = Optional.of("value");
        String result = optionalOperations.orElseThrow(optional, () -> new RuntimeException("Not found"));
        assertEquals("value", result);
    }

    @Test
    void testOrElseThrowWithException() {
        Optional<String> emptyOptional = Optional.empty();
        assertThrows(RuntimeException.class, () ->
                optionalOperations.orElseThrow(emptyOptional, () -> new RuntimeException("Not found"))
        );
    }

    @Test
    void testOrElseThrowIllegalArgument() {
        Optional<Integer> optional = Optional.of(42);
        Integer result = optionalOperations.orElseThrowIllegalArgument(optional, "Value is required");
        assertEquals(42, result);
    }

    @Test
    void testOrElseThrowIllegalArgumentWithException() {
        Optional<Integer> emptyOptional = Optional.empty();
        assertThrows(IllegalArgumentException.class, () ->
                optionalOperations.orElseThrowIllegalArgument(emptyOptional, "Value is required")
        );
    }

    // ==================== Advanced Transformation Tests ====================

    @Test
    void testTransformOrFallbackPrimary() {
        Optional<String> optional = Optional.of("123");
        Optional<Integer> result = optionalOperations.transformOrFallback(
                optional,
                s -> optionalOperations.safeParseInt(s),
                s -> Optional.of(0)
        );
        assertTrue(result.isPresent());
        assertEquals(123, result.get());
    }

    @Test
    void testTransformOrFallbackUseFallback() {
        Optional<String> optional = Optional.of("invalid");
        Optional<Integer> result = optionalOperations.transformOrFallback(
                optional,
                s -> optionalOperations.safeParseInt(s),
                s -> Optional.of(999)
        );
        assertTrue(result.isPresent());
        assertEquals(999, result.get());
    }

    @Test
    void testMapAndFilter() {
        Optional<String> optional = Optional.of("hello");
        Optional<Integer> result = optionalOperations.mapAndFilter(
                optional,
                String::length,
                len -> len > 3
        );
        assertTrue(result.isPresent());
        assertEquals(5, result.get());
    }

    @Test
    void testMapAndFilterFails() {
        Optional<String> optional = Optional.of("hi");
        Optional<Integer> result = optionalOperations.mapAndFilter(
                optional,
                String::length,
                len -> len > 5
        );
        assertTrue(result.isEmpty());
    }

    @Test
    void testNullSafeTransformWithValue() {
        String result = optionalOperations.nullSafeTransform(
                "test",
                String::toUpperCase,
                "default"
        );
        assertEquals("TEST", result);
    }

    @Test
    void testNullSafeTransformWithNull() {
        String result = optionalOperations.nullSafeTransform(
                (String) null,
                s -> s.toUpperCase(),
                "default"
        );
        assertEquals("default", result);
    }

    // ==================== Stream Operations Tests ====================

    @Test
    void testFilterUsersByAgeRange() {
        List<OptionalOperations.User> users = Arrays.asList(
                new OptionalOperations.User("Alice", Optional.of("alice@example.com"), Optional.of(25)),
                new OptionalOperations.User("Bob", Optional.of("bob@example.com"), Optional.of(35)),
                new OptionalOperations.User("Charlie", Optional.empty(), Optional.of(45)),
                new OptionalOperations.User("Diana", Optional.of("diana@example.com"), Optional.empty())
        );

        List<OptionalOperations.User> result = optionalOperations.filterUsersByAgeRange(users, 30, 45);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(u -> u.getAge().isPresent() && u.getAge().get() >= 30 && u.getAge().get() <= 45));
    }

    @Test
    void testGetAllUserEmailsUpperCase() {
        List<OptionalOperations.User> users = Arrays.asList(
                new OptionalOperations.User("Alice", Optional.of("alice@example.com"), Optional.of(25)),
                new OptionalOperations.User("Bob", Optional.of("bob@example.com"), Optional.of(30)),
                new OptionalOperations.User("Charlie", Optional.empty(), Optional.of(35))
        );

        List<String> emails = optionalOperations.getAllUserEmailsUpperCase(users);
        assertEquals(2, emails.size());
        assertTrue(emails.contains("ALICE@EXAMPLE.COM"));
        assertTrue(emails.contains("BOB@EXAMPLE.COM"));
    }

    @Test
    void testGroupUsersByEmailPresence() {
        List<OptionalOperations.User> users = Arrays.asList(
                new OptionalOperations.User("Alice", Optional.of("alice@example.com"), Optional.of(25)),
                new OptionalOperations.User("Bob", Optional.of("bob@example.com"), Optional.of(30)),
                new OptionalOperations.User("Charlie", Optional.empty(), Optional.of(35))
        );

        java.util.Map<String, List<OptionalOperations.User>> groups = optionalOperations.groupUsersByEmailPresence(users);
        assertEquals(2, groups.get("withEmail").size());
        assertEquals(1, groups.get("withoutEmail").size());
        assertTrue(groups.get("withoutEmail").get(0).getName().equals("Charlie"));
    }
}
