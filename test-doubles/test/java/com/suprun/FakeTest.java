package com.suprun;

import com.suprun.model.User;
import com.suprun.repository.FakeUserRepository;
import com.suprun.repository.UserRepository;
import com.suprun.service.UserService;
import com.suprun.testutil.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test demonstrating the Fake test double pattern.
 *
 * <h2>Pattern Description:</h2>
 * A Fake is a working implementation of a component that provides simplified
 * behavior. Unlike stubs or mocks, a Fake implements real logic, but in a
 * lightweight or simplified way (e.g., in-memory storage instead of database).
 *
 * <h2>When to Use:</h2>
 * Use a Fake when:
 * <ul>
 *   <li>You need a working implementation but want to avoid external dependencies</li>
 *   <li>The real implementation is slow or expensive (e.g., database, file I/O)</li>
 *   <li>You need deterministic behavior for testing</li>
 *   <li>Testing a component that heavily depends on an interface</li>
 * </ul>
 *
 * <h2>Advantages:</h2>
 * <ul>
 *   <li>Real implementation - exercises more of your code</li>
 *   <li>Faster than external dependencies</li>
 *   <li>Deterministic - easier to debug</li>
 *   <li>Can reuse in multiple tests</li>
 * </ul>
 *
 * <h2>Disadvantages:</h2>
 * <ul>
 *   <li>Requires maintaining the Fake implementation</li>
 *   <li>May not catch issues specific to real implementation</li>
 *   <li>Can become complex if overloaded with features</li>
 * </ul>
 *
 * <h2>Key Implementation:</h2>
 * The {@link FakeUserRepository} is an in-memory implementation of
 * {@link UserRepository} that stores users in a HashMap.
 *
 * @author Yurii_Suprun
 * @see FakeUserRepository
 * @see <a href="https://xunitpatterns.com/Fake%20Object.html">Fake Object Pattern</a>
 */
@SpringBootTest
public class FakeTest {

    private UserRepository fakeRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        // Initialize with the Fake implementation
        fakeRepository = new FakeUserRepository();
        userService = new UserService(fakeRepository);
    }

    /**
     * Demonstrates basic save and retrieve operations using a Fake.
     * 
     * This test shows that the Fake provides real storage semantics
     * without needing a real database.
     */
    @Test
    void testSaveAndRetrieveWithFake() {
        // Arrange
        User user = new UserBuilder()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john@example.com")
                .build();

        // Act
        userService.saveUser(user);
        Optional<User> retrievedUser = fakeRepository.findById(1L);

        // Assert
        assertTrue(retrievedUser.isPresent(), "User should be retrievable after save");
        assertEquals("John Doe", retrievedUser.get().getName());
        assertEquals("john@example.com", retrievedUser.get().getEmail());
    }

    /**
     * Demonstrates the Fake's state management capabilities.
     * 
     * Multiple operations are performed to show that the Fake maintains state
     * across operations, just like a real database would.
     */
    @Test
    void testFakeMaintainsState() {
        // Arrange: Create multiple users
        User user1 = UserBuilder.createUserWithId(1L);
        User user2 = UserBuilder.createUserWithId(2L);

        // Act: Save users
        fakeRepository.save(user1);
        fakeRepository.save(user2);

        // Assert: Verify state is maintained
        assertEquals(2, fakeRepository.count(), "Fake should maintain count");
        assertTrue(fakeRepository.existsById(1L), "User 1 should exist");
        assertTrue(fakeRepository.existsById(2L), "User 2 should exist");
    }

    /**
     * Demonstrates deletion behavior in the Fake.
     */
    @Test
    void testFakeSupportsDelete() {
        // Arrange
        User user = UserBuilder.createUserWithId(5L);
        fakeRepository.save(user);

        // Act
        fakeRepository.deleteById(5L);

        // Assert
        assertFalse(fakeRepository.existsById(5L), "User should not exist after delete");
        assertEquals(0, fakeRepository.count(), "Fake should have no users");
    }

    /**
     * Demonstrates that the Fake retrieves all users.
     */
    @Test
    void testFakeCanRetrieveAllUsers() {
        // Arrange: Create and save multiple users
        fakeRepository.save(UserBuilder.createUserWithId(1L));
        fakeRepository.save(UserBuilder.createUserWithId(2L));
        fakeRepository.save(UserBuilder.createUserWithId(3L));

        // Act
        var allUsers = fakeRepository.findAll();

        // Assert
        assertEquals(3, allUsers.size(), "Fake should return all saved users");
    }

    /**
     * Demonstrates isolation between test runs.
     * 
     * Each test gets a fresh FakeUserRepository instance (created in setUp),
     * so tests don't interfere with each other.
     */
    @Test
    void testFakeIsolationBetweenTests() {
        // This test should start with an empty Fake
        assertEquals(0, fakeRepository.count(), "Fake should start empty for each test");

        User user = UserBuilder.createDefaultUser();
        fakeRepository.save(user);

        assertEquals(1, fakeRepository.count(), "Fake should have one user after save");
    }
}
