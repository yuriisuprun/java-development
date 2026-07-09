package com.suprun.coding;

import java.util.List;
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

    // ==================== User Domain Model ====================

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
}
