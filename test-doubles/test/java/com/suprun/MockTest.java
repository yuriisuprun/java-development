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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test demonstrating the Mock test double pattern.
 *
 * <h2>Pattern Description:</h2>
 * A Mock is a test double that both provides predetermined behavior AND
 * verifies that expected methods are called with expected parameters.
 * Mocks enforce interaction contracts between components.
 *
 * <h2>When to Use:</h2>
 * Use a Mock when:
 * <ul>
 *   <li>You need to verify that a method was called with specific arguments</li>
 *   <li>You want to enforce an interaction contract</li>
 *   <li>You need to verify the number of times a method is called</li>
 *   <li>You care about HOW a component is used, not just its return value</li>
 * </ul>
 *
 * <h2>Advantages:</h2>
 * <ul>
 *   <li>Verifies interactions - catches when code stops calling expected methods</li>
 *   <li>Enforces behavior contracts</li>
 *   <li>Helps detect over/under-calling of methods</li>
 *   <li>Excellent for testing error handling and edge cases</li>
 * </ul>
 *
 * <h2>Disadvantages:</h2>
 * <ul>
 *   <li>Can lead to brittle tests that fail when implementation details change</li>
 *   <li>Over-specification of interactions can make tests rigid</li>
 *   <li>Can be misused to test implementation instead of behavior</li>
 * </ul>
 *
 * <h2>Key Difference from Stub:</h2>
 * Mocks verify method calls, Stubs only provide return values.
 * Use Mocks when interaction verification matters, Stubs for simple return values.
 *
 * @author Yurii_Suprun
 * @see StubTest
 * @see <a href="https://xunitpatterns.com/Mock%20Object.html">Mock Object Pattern</a>
 */
@SpringBootTest
public class MockTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    /**
     * Demonstrates basic mock verification.
     * 
     * The mock verifies that save() was called on the repository
     * with the expected user object.
     */
    @Test
    void testWithMock() {
        // Arrange
        User user = new UserBuilder()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john@example.com")
                .build();

        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Act
        userService.saveUser(user);

        // Assert: Verify the save method was called with the user
        verify(userRepository).save(user);
    }

    /**
     * Demonstrates verifying the number of times a method is called.
     */
    @Test
    void testVerifyCallCount() {
        // Arrange
        User user1 = UserBuilder.createUserWithId(1L);
        User user2 = UserBuilder.createUserWithId(2L);

        Mockito.when(userRepository.save(any())).thenReturn(null);

        // Act: Save two users
        userService.saveUser(user1);
        userService.saveUser(user2);

        // Assert: Verify save was called exactly twice
        verify(userRepository, times(2)).save(any());
    }

    /**
     * Demonstrates verifying that a method was never called.
     */
    @Test
    void testVerifyNeverCalled() {
        // Arrange - no stub for findById

        // Act: Only call processUser, not any repository method
        User dummyUser = UserBuilder.createDefaultUser();
        userService.processUser(dummyUser);

        // Assert: Verify that repository methods were never called
        verify(userRepository, never()).findById(any());
        verify(userRepository, never()).save(any());
    }

    /**
     * Demonstrates verifying method calls with specific arguments.
     * 
     * Uses ArgumentMatchers to verify calls were made with expected values.
     */
    @Test
    void testVerifyWithArgumentMatchers() {
        // Arrange
        User user = UserBuilder.createUserWithId(5L);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Act
        userService.saveUser(user);

        // Assert: Verify the exact user was saved
        verify(userRepository).save(user);
    }

    /**
     * Demonstrates multiple verifications in a single test.
     * 
     * Verifies the sequence and count of interactions.
     */
    @Test
    void testMultipleVerifications() {
        // Arrange
        User user = UserBuilder.createDefaultUser();
        Mockito.when(userRepository.save(any())).thenReturn(user);

        // Act
        userService.saveUser(user);

        // Assert: Multiple verifications
        verify(userRepository).save(any());              // Called at least once
        verify(userRepository, times(1)).save(any());    // Called exactly once
        
        // Verify another method was NOT called
        verify(userRepository, never()).delete(any());
    }

    /**
     * Demonstrates mock behavior with exception handling.
     * 
     * Verifies that exception handling code calls appropriate methods.
     */
    @Test
    void testVerifyBehaviorOnException() {
        // Arrange: Configure mock to throw exception
        User user = UserBuilder.createDefaultUser();
        Mockito.when(userRepository.save(user))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert: Even though save throws, verify it was called
        try {
            userService.saveUser(user);
        } catch (RuntimeException e) {
            // Expected
        }

        // Verify save was called despite the exception
        verify(userRepository).save(user);
    }

    /**
     * Demonstrates verifying no interactions at all.
     * 
     * Useful for testing that mocked dependencies are not accessed
     * under certain conditions.
     */
    @Test
    void testVerifyNoInteractions() {
        // Arrange - mock repository

        // Act: Call a service method that doesn't use repository
        User dummyUser = UserBuilder.createDefaultUser();
        userService.processUser(dummyUser);

        // Assert: Verify repository was never used at all
        Mockito.verifyNoInteractions(userRepository);
    }
}
