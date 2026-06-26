package com.suprun;

import com.suprun.model.User;
import com.suprun.repository.UserRepository;
import com.suprun.service.UserService;
import com.suprun.testutil.UserBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test demonstrating the Stub test double pattern.
 *
 * <h2>Pattern Description:</h2>
 * A Stub is a test double that provides predetermined answers to method calls.
 * Stubs do NOT verify that methods were called - they simply return configured values.
 *
 * <h2>When to Use:</h2>
 * Use a Stub when:
 * <ul>
 *   <li>You need specific return values to test business logic</li>
 *   <li>You want to test different code paths without caring how methods are called</li>
 *   <li>You're testing a component that depends on external data</li>
 *   <li>You don't care about verifying the interaction, only the return value</li>
 * </ul>
 *
 * <h2>Advantages:</h2>
 * <ul>
 *   <li>Simple - focused on return values</li>
 *   <li>Tests behavior, not implementation</li>
 *   <li>Can test multiple code paths easily</li>
 * </ul>
 *
 * <h2>Disadvantages:</h2>
 * <ul>
 *   <li>Doesn't verify that methods are actually called</li>
 *   <li>Can make tests fragile if expectations change</li>
 *   <li>No verification of interaction patterns</li>
 * </ul>
 *
 * <h2>Key Difference from Mock:</h2>
 * Stubs provide return values but do NOT verify method calls.
 * For verification, use {@link MockTest} instead.
 *
 * @author Yurii_Suprun
 * @see MockTest
 * @see <a href="https://xunitpatterns.com/Stub.html">Stub Pattern</a>
 */
@SpringBootTest
public class StubTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    /**
     * Demonstrates basic stubbing of a method return value.
     * 
     * The stub configures the repository to return a specific user when
     * findById is called with a specific ID.
     */
    @Test
    void testWithStub() {
        // Arrange: Create test user
        User user = new UserBuilder()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john@example.com")
                .build();

        // Configure stub to return this user
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act: Call service method
        Optional<User> retrievedUser = userService.getUserById(1L);

        // Assert: Verify the returned data is correct
        assertTrue(retrievedUser.isPresent(), "User should be present");
        assertEquals("John Doe", retrievedUser.get().getName());
    }

    /**
     * Demonstrates stubbing different return values for different inputs.
     * 
     * This shows how stubs can be configured to return different values
     * based on input parameters, enabling testing of multiple code paths.
     */
    @Test
    void testStubWithDifferentInputs() {
        // Arrange: Configure stubs for different IDs
        User user1 = UserBuilder.createUserWithId(1L);
        User user2 = UserBuilder.createUserWithId(2L);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        // Act & Assert: Verify each stubbed call returns correct user
        Optional<User> retrieved1 = userService.getUserById(1L);
        assertTrue(retrieved1.isPresent());

        Optional<User> retrieved2 = userService.getUserById(2L);
        assertTrue(retrieved2.isPresent());
    }

    /**
     * Demonstrates stubbing to test error handling paths.
     * 
     * By stubbing a method to return empty Optional, we can test how
     * the code handles the absence of data.
     */
    @Test
    void testStubReturnsEmpty() {
        // Arrange: Configure stub to return empty
        Mockito.when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<User> retrievedUser = userService.getUserById(999L);

        // Assert
        assertTrue(retrievedUser.isEmpty(), "User should not be present for non-existent ID");
    }

    /**
     * Demonstrates that stubs don't verify method invocation.
     * 
     * Unlike mocks, stubs don't care if the stubbed method is actually called.
     * This test focuses on the return value, not the call verification.
     */
    @Test
    void testStubDoesNotVerifyInvocation() {
        // Arrange: Configure stub (but won't call it)
        User user = UserBuilder.createDefaultUser();
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act: Don't call userService.getUserById - just verify behavior doesn't depend on it

        // Assert: This test passes because stubs don't verify calls
        // (If this were a Mock with verify(), it would fail)
        assertTrue(true, "Stub configured but not called - that's ok");
    }

    /**
     * Demonstrates stubbing with default return values.
     * 
     * When a method is called with arguments not explicitly stubbed,
     * Mockito returns default values (null for objects, 0 for primitives, etc.).
     */
    @Test
    void testStubWithDefaultBehavior() {
        // Arrange: Only stub specific ID
        User user = UserBuilder.createUserWithId(1L);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        // No stub for ID 2, so it will return the default

        // Act
        Optional<User> retrieved1 = userService.getUserById(1L);
        Optional<User> retrieved2 = userService.getUserById(2L);

        // Assert
        assertTrue(retrieved1.isPresent(), "Stubbed ID should return user");
        assertTrue(retrieved2.isEmpty(), "Non-stubbed ID returns empty Optional");
    }
}
