package com.suprun.testutil;

import com.suprun.model.User;

/**
 * Builder for creating User test data with sensible defaults.
 *
 * This builder pattern provides a fluent API for creating User objects
 * in tests, making test code more readable and maintainable.
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * User user = new UserBuilder()
 *     .withId(1L)
 *     .withName("John Doe")
 *     .build();
 * </pre>
 *
 * @author Yurii_Suprun
 */
public class UserBuilder {
    private Long id = 1L;
    private String name = "Test User";
    private String email = "test@example.com";

    /**
     * Sets the user ID.
     *
     * @param id the user ID
     * @return this builder for method chaining
     */
    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the user name.
     *
     * @param name the user name
     * @return this builder for method chaining
     */
    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the user email.
     *
     * @param email the user email
     * @return this builder for method chaining
     */
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Builds and returns the User instance with configured values.
     *
     * @return a new User instance
     */
    public User build() {
        return new User(id, name, email);
    }

    /**
     * Creates a User with default values.
     *
     * @return a new User instance with default values
     */
    public static User createDefaultUser() {
        return new UserBuilder().build();
    }

    /**
     * Creates a User with specified ID.
     *
     * @param id the user ID
     * @return a new User instance
     */
    public static User createUserWithId(Long id) {
        return new UserBuilder().withId(id).build();
    }

    /**
     * Creates a User with specified name.
     *
     * @param name the user name
     * @return a new User instance
     */
    public static User createUserWithName(String name) {
        return new UserBuilder().withName(name).build();
    }
}
