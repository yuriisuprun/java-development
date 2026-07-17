package com.suprun.coding;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Java 8+ Optional operations and best practices.
 * Demonstrates idiomatic handling of null values with the Optional API.
 *
 * <p>Key principles:
 * <ul>
 *   <li>Use Optional to avoid null pointer exceptions</li>
 *   <li>Prefer functional transformations over imperative checking</li>
 *   <li>Use lazy evaluation where appropriate (orElseGet vs orElse)</li>
 *   <li>Chain operations fluently rather than nested conditionals</li>
 * </ul>
 */
public class OptionalOperations {

    // ==================== Wrapping and Unwrapping ====================

    /**
     * Wraps a potentially null value into an Optional.
     *
     * @param value the value to wrap (may be null)
     * @param <T> the type of the value
     * @return Optional containing the value, or empty if null
     */
    public <T> Optional<T> wrapValue(T value) {
        return Optional.ofNullable(value);
    }

    /**
     * Retrieves value from Optional with a static default.
     *
     * @param optional the Optional to process
     * @param defaultValue the default value if Optional is empty
     * @param <T> the type of the value
     * @return the value or default if empty
     */
    public <T> T getOrDefault(Optional<T> optional, T defaultValue) {
        return optional.orElse(defaultValue);
    }

    /**
     * Retrieves value from Optional with lazy-evaluated default.
     * Prefer this over orElse when the default is expensive to compute.
     *
     * @param optional the Optional to process
     * @param supplier the supplier providing the default value
     * @param <T> the type of the value
     * @return the value or supplier result if empty
     */
    public <T> T getOrSupply(Optional<T> optional, Supplier<T> supplier) {
        return optional.orElseGet(supplier);
    }

    // ==================== Transformations ====================

    /**
     * Transforms an Optional value using a mapping function.
     *
     * @param optional the Optional to process
     * @param transformer the transformation function
     * @param <T> the input type
     * @param <R> the output type
     * @return Optional containing transformed value, or empty if input was empty
     */
    public <T, R> Optional<R> transformValue(Optional<T> optional, Function<T, R> transformer) {
        return optional.map(transformer);
    }

    /**
     * Chains Optional operations using flatMap.
     * Use this when the transformer itself returns an Optional.
     *
     * @param optional the Optional to process
     * @param chain the chaining function that returns an Optional
     * @param <T> the input type
     * @param <R> the output type
     * @return the result of chaining, or empty if any step was empty
     */
    public <T, R> Optional<R> chainOperations(Optional<T> optional, Function<T, Optional<R>> chain) {
        return optional.flatMap(chain);
    }

    /**
     * Filters an Optional value based on a predicate.
     *
     * @param optional the Optional to filter
     * @param predicate the condition to test
     * @param <T> the type of the value
     * @return Optional containing value if predicate is true, otherwise empty
     */
    public <T> Optional<T> filterValue(Optional<T> optional, Predicate<T> predicate) {
        return optional.filter(predicate);
    }

    /**
     * Combines two Optional values into a single Optional.
     *
     * @param opt1 first Optional
     * @param opt2 second Optional
     * @return Optional containing concatenated values, or empty if either input is empty
     */
    public Optional<String> combineOptionals(Optional<String> opt1, Optional<String> opt2) {
        return opt1.flatMap(first -> opt2.map(second -> first + " " + second));
    }

    // ==================== Side Effects ====================

    /**
     * Performs an action if the Optional is present.
     *
     * @param optional the Optional to check
     * @param action the action to perform if present
     * @param <T> the type of the value
     */
    public <T> void ifPresentAction(Optional<T> optional, Consumer<T> action) {
        optional.ifPresent(action);
    }

    /**
     * Performs different actions based on Optional presence.
     * Available in Java 9+.
     *
     * @param optional the Optional to check
     * @param presentAction action to perform if present
     * @param emptyAction action to perform if empty
     * @param <T> the type of the value
     */
    public <T> void ifPresentOrElse(Optional<T> optional, Consumer<T> presentAction, Runnable emptyAction) {
        optional.ifPresentOrElse(presentAction, emptyAction);
    }

    // ==================== Domain-Specific Operations ====================

    /**
     * Finds a user by name (case-insensitive) from a list.
     *
     * @param users the list of users to search
     * @param name the name to search for
     * @return Optional containing the user if found
     */
    public Optional<User> findUserByName(List<User> users, String name) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Retrieves a user's email address with a default fallback.
     *
     * @param users the list of users
     * @param name the user's name to search for
     * @return the user's email, or default if not found or email is absent
     */
    public String getUserEmailOrDefault(List<User> users, String name) {
        return findUserByName(users, name)
                .flatMap(User::getEmail)
                .orElse("unknown@example.com");
    }

    /**
     * Collects all email addresses from users, gracefully handling absent emails.
     * Uses flatMap to unwrap Optional values elegantly.
     *
     * @param users the list of users
     * @return list of email addresses (empty emails are excluded)
     */
    public List<String> getAllUserEmails(List<User> users) {
        return users.stream()
                .flatMap(user -> user.getEmail().stream())
                .collect(Collectors.toList());
    }

    // ==================== Exception Handling ====================

    /**
     * Converts an exception-throwing operation into an Optional.
     * Useful for wrapping operations that might throw exceptions.
     *
     * @param supplier the supplier that might throw an exception
     * @param <T> the type of the value
     * @return Optional containing the value, or empty if exception occurs
     */
    public <T> Optional<T> tryOperation(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Parses a string to integer safely using Optional.
     *
     * @param value the string to parse
     * @return Optional containing the parsed integer, or empty if parsing fails
     */
    public Optional<Integer> safeParseInt(String value) {
        return tryOperation(() -> Integer.parseInt(value));
    }

    /**
     * Parses a string to double safely using Optional.
     *
     * @param value the string to parse
     * @return Optional containing the parsed double, or empty if parsing fails
     */
    public Optional<Double> safeParseDouble(String value) {
        return tryOperation(() -> Double.parseDouble(value));
    }

    // ==================== Multiple Optional Combinations ====================

    /**
     * Combines three Optional values.
     *
     * @param opt1 first Optional
     * @param opt2 second Optional
     * @param opt3 third Optional
     * @param combiner function to combine three values
     * @param <T1> type of first value
     * @param <T2> type of second value
     * @param <T3> type of third value
     * @param <R> result type
     * @return Optional containing combined result, or empty if any input is empty
     */
    public <T1, T2, T3, R> Optional<R> combineThree(
            Optional<T1> opt1,
            Optional<T2> opt2,
            Optional<T3> opt3,
            ThreeValueCombiner<T1, T2, T3, R> combiner) {
        return opt1.flatMap(v1 ->
                opt2.flatMap(v2 ->
                        opt3.map(v3 -> combiner.combine(v1, v2, v3))
                )
        );
    }

    /**
     * Combines four Optional values.
     *
     * @param opt1 first Optional
     * @param opt2 second Optional
     * @param opt3 third Optional
     * @param opt4 fourth Optional
     * @param combiner function to combine four values
     * @param <T1> type of first value
     * @param <T2> type of second value
     * @param <T3> type of third value
     * @param <T4> type of fourth value
     * @param <R> result type
     * @return Optional containing combined result, or empty if any input is empty
     */
    public <T1, T2, T3, T4, R> Optional<R> combineFour(
            Optional<T1> opt1,
            Optional<T2> opt2,
            Optional<T3> opt3,
            Optional<T4> opt4,
            FourValueCombiner<T1, T2, T3, T4, R> combiner) {
        return opt1.flatMap(v1 ->
                opt2.flatMap(v2 ->
                        opt3.flatMap(v3 ->
                                opt4.map(v4 -> combiner.combine(v1, v2, v3, v4))
                        )
                )
        );
    }

    // ==================== Conditional Chaining ====================

    /**
     * Chains multiple filter operations with logical AND.
     * Stops at the first filter that fails (short-circuits).
     *
     * @param optional the Optional to filter
     * @param predicates variable number of predicates to apply
     * @param <T> the type of the value
     * @return Optional containing value if all predicates pass, otherwise empty
     */
    @SafeVarargs
    public final <T> Optional<T> filterAll(Optional<T> optional, Predicate<T>... predicates) {
        Optional<T> result = optional;
        for (Predicate<T> predicate : predicates) {
            result = result.filter(predicate);
            if (result.isEmpty()) {
                break;
            }
        }
        return result;
    }

    /**
     * Chains multiple filter operations with logical OR.
     * Passes if ANY predicate is true.
     *
     * @param optional the Optional to filter
     * @param predicates variable number of predicates to apply
     * @param <T> the type of the value
     * @return Optional containing value if any predicate passes, otherwise empty
     */
    @SafeVarargs
    public final <T> Optional<T> filterAny(Optional<T> optional, Predicate<T>... predicates) {
        if (optional.isEmpty()) {
            return optional;
        }
        T value = optional.get();
        for (Predicate<T> predicate : predicates) {
            if (predicate.test(value)) {
                return optional;
            }
        }
        return Optional.empty();
    }

    // ==================== Validation Chains ====================

    /**
     * Validates a user and returns validation errors wrapped in Optional.
     * If valid, returns empty Optional (no errors).
     *
     * @param user the user to validate
     * @return Optional containing validation error message, or empty if valid
     */
    public Optional<String> validateUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            return Optional.of("User name cannot be empty");
        }
        if (user.getAge().isPresent() && user.getAge().get() < 0) {
            return Optional.of("User age cannot be negative");
        }
        if (user.getEmail().isPresent() && !user.getEmail().get().contains("@")) {
            return Optional.of("User email is invalid");
        }
        return Optional.empty();
    }

    /**
     * Validates a list of users and collects all validation errors.
     *
     * @param users the users to validate
     * @return list of error messages (empty if all users are valid)
     */
    public List<String> validateAllUsers(List<User> users) {
        return users.stream()
                .map(this::validateUser)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    // ==================== Optional to Exception ====================

    /**
     * Extracts value from Optional or throws a custom exception.
     *
     * @param optional the Optional to extract from
     * @param exceptionSupplier the supplier providing the exception to throw
     * @param <T> the type of the value
     * @param <E> the exception type
     * @return the value if present
     * @throws E if Optional is empty
     */
    public <T, E extends Exception> T orElseThrow(Optional<T> optional, Supplier<E> exceptionSupplier) throws E {
        return optional.orElseThrow(exceptionSupplier);
    }

    /**
     * Extracts value from Optional or throws IllegalArgumentException with a message.
     *
     * @param optional the Optional to extract from
     * @param errorMessage the error message for the exception
     * @param <T> the type of the value
     * @return the value if present
     * @throws IllegalArgumentException if Optional is empty
     */
    public <T> T orElseThrowIllegalArgument(Optional<T> optional, String errorMessage) {
        return optional.orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    // ==================== Advanced Transformations ====================

    /**
     * Applies a transformation that returns Optional, with fallback.
     * If the transformation results in empty, tries the fallback.
     *
     * @param optional the Optional to transform
     * @param transformer the primary transformer
     * @param fallback the fallback transformer to use if primary returns empty
     * @param <T> the input type
     * @param <R> the output type
     * @return the result of primary transformer, or fallback if empty
     */
    public <T, R> Optional<R> transformOrFallback(
            Optional<T> optional,
            Function<T, Optional<R>> transformer,
            Function<T, Optional<R>> fallback) {
        return optional.flatMap(value -> transformer.apply(value)
                .or(() -> fallback.apply(value)));
    }

    /**
     * Maps a value and then applies a filter in a single operation.
     *
     * @param optional the Optional to process
     * @param transformer the transformation function
     * @param filter the filter predicate to apply to the transformed value
     * @param <T> the input type
     * @param <R> the output type
     * @return Optional with transformed value if filter passes, otherwise empty
     */
    public <T, R> Optional<R> mapAndFilter(
            Optional<T> optional,
            Function<T, R> transformer,
            Predicate<R> filter) {
        return optional.map(transformer).filter(filter);
    }

    /**
     * Applies a null-safe transformation using two separate functions.
     * If value is null or transformation returns null, uses fallback.
     *
     * @param value the value to transform (may be null)
     * @param transformer the transformation function
     * @param fallback the fallback value
     * @param <T> the input type
     * @param <R> the output type
     * @return transformed value or fallback
     */
    public <T, R> R nullSafeTransform(T value, Function<T, R> transformer, R fallback) {
        return Optional.ofNullable(value)
                .map(transformer)
                .orElse(fallback);
    }

    // ==================== Stream Operations ====================

    /**
     * Filters and transforms users by age range.
     *
     * @param users the list of users
     * @param minAge minimum age (inclusive)
     * @param maxAge maximum age (inclusive)
     * @return list of users within age range
     */
    public List<User> filterUsersByAgeRange(List<User> users, int minAge, int maxAge) {
        return users.stream()
                .filter(user -> user.getAge()
                        .filter(age -> age >= minAge && age <= maxAge)
                        .isPresent())
                .collect(Collectors.toList());
    }

    /**
     * Extracts all valid emails from users and converts to uppercase.
     *
     * @param users the list of users
     * @return list of uppercase email addresses
     */
    public List<String> getAllUserEmailsUpperCase(List<User> users) {
        return users.stream()
                .flatMap(user -> user.getEmail().stream())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    /**
     * Groups users by presence of email.
     *
     * @param users the list of users
     * @return map with keys "withEmail" and "withoutEmail" containing respective user lists
     */
    public Map<String, List<User>> groupUsersByEmailPresence(List<User> users) {
        Map<String, List<User>> result = new java.util.HashMap<>();
        result.put("withEmail", users.stream()
                .filter(user -> user.getEmail().isPresent())
                .collect(Collectors.toList()));
        result.put("withoutEmail", users.stream()
                .filter(user -> user.getEmail().isEmpty())
                .collect(Collectors.toList()));
        return result;
    }

    /**
     * User data class for demonstration purposes.
     * Demonstrates practical use of Optional for optional fields.
     */
    public static class User {
        private final String name;
        private final Optional<String> email;
        private final Optional<Integer> age;

        /**
         * Creates a new User.
         *
         * @param name the user's name (required)
         * @param email the user's email (optional)
         * @param age the user's age (optional)
         */
        public User(String name, Optional<String> email, Optional<Integer> age) {
            this.name = name;
            this.email = email;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Optional<String> getEmail() {
            return email;
        }

        public Optional<Integer> getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", email=" + email +
                    ", age=" + age +
                    '}';
        }
    }

    // ==================== Functional Interfaces ====================

    /**
     * Functional interface for combining three values.
     */
    @FunctionalInterface
    public interface ThreeValueCombiner<T1, T2, T3, R> {
        R combine(T1 first, T2 second, T3 third);
    }

    /**
     * Functional interface for combining four values.
     */
    @FunctionalInterface
    public interface FourValueCombiner<T1, T2, T3, T4, R> {
        R combine(T1 first, T2 second, T3 third, T4 fourth);
    }
}
