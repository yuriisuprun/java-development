package com.suprun.coding;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Java 8 Optional operations and best practices.
 * Demonstrates handling null values with Optional API.
 */
public class OptionalOperations {

    /**
     * Convert a potentially null value into an Optional.
     *
     * @param value the value to wrap
     * @param <T>   the type of the value
     * @return Optional containing the value or Optional.empty() if null
     */
    public <T> Optional<T> wrapValue(T value) {
        return Optional.ofNullable(value);
    }

    /**
     * Get value from Optional with a default value.
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
     * Get value from Optional with a supplier for lazy evaluation.
     *
     * @param optional the Optional to process
     * @param supplier the supplier to provide default value
     * @param <T> the type of the value
     * @return the value or default from supplier if empty
     */
    public <T> T getOrSupply(Optional<T> optional, java.util.function.Supplier<T> supplier) {
        return optional.orElseGet(supplier);
    }

    /**
     * Transform Optional value using a mapping function.
     *
     * @param optional the Optional to process
     * @param transformer the transformation function
     * @param <T> the input type
     * @param <R> the output type
     * @return Optional of transformed value or empty
     */
    public <T, R> Optional<R> transformValue(Optional<T> optional, java.util.function.Function<T, R> transformer) {
        return optional.map(transformer);
    }

    /**
     * Chain Optional operations using flatMap.
     *
     * @param optional the Optional to process
     * @param chain the chaining function returning Optional
     * @param <T> the input type
     * @param <R> the output type
     * @return Optional result of chained operation
     */
    public <T, R> Optional<R> chainOperations(Optional<T> optional, java.util.function.Function<T, Optional<R>> chain) {
        return optional.flatMap(chain);
    }

    /**
     * Filter Optional value based on a predicate.
     *
     * @param optional the Optional to filter
     * @param predicate the condition to check
     * @param <T> the type of the value
     * @return Optional of value if predicate is true, otherwise empty
     */
    public <T> Optional<T> filterValue(Optional<T> optional, java.util.function.Predicate<T> predicate) {
        return optional.filter(predicate);
    }

    /**
     * Check if Optional is present and perform action.
     *
     * @param optional the Optional to check
     * @param action the action to perform if present
     * @param <T> the type of the value
     */
    public <T> void ifPresentAction(Optional<T> optional, java.util.function.Consumer<T> action) {
        optional.ifPresent(action);
    }

    /**
     * Perform different actions based on Optional presence.
     *
     * @param optional the Optional to check
     * @param presentAction action if present
     * @param emptyAction action if empty
     * @param <T> the type of the value
     */
    public <T> void ifPresentOrElse(Optional<T> optional, java.util.function.Consumer<T> presentAction, Runnable emptyAction) {
        optional.ifPresentOrElse(presentAction, emptyAction);
    }

    /**
     * Find a user by name from a list, returning Optional.
     *
     * @param users the list of users
     * @param name the name to search for
     * @return Optional of the user if found
     */
    public Optional<User> findUserByName(List<User> users, String name) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Get user email with default value.
     *
     * @param users the list of users
     * @param name the name to search for
     * @return email or "unknown@example.com" if not found
     */
    public String getUserEmailOrDefault(List<User> users, String name) {
        return findUserByName(users, name)
                .flatMap(User::getEmail)
                .orElse("unknown@example.com");
    }

    /**
     * Get all email addresses from users, filtering out nulls gracefully.
     *
     * @param users the list of users
     * @return list of non-null email addresses
     */
    public List<String> getAllUserEmails(List<User> users) {
        return users.stream()
                .map(u -> u.getEmail())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /**
     * Combine multiple Optional values.
     *
     * @param opt1 first Optional
     * @param opt2 second Optional
     * @return combined result or empty
     */
    public Optional<String> combineOptionals(Optional<String> opt1, Optional<String> opt2) {
        return opt1.flatMap(first -> opt2.map(second -> first + " " + second));
    }

    /**
     * User data class for demonstration.
     */
    public static class User {
        private String name;
        private Optional<String> email;
        private Optional<Integer> age;

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
