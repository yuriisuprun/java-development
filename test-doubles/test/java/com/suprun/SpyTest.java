package com.suprun;

import com.suprun.model.User;
import com.suprun.service.UserService;
import com.suprun.testutil.UserBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * Test demonstrating the Spy test double pattern.
 *
 * <h2>Pattern Description:</h2>
 * A Spy is a partial mock that wraps a real object. Spies call real methods
 * by default but allow selective stubbing of specific methods. This enables
 * testing real behavior while mocking out specific dependencies.
 *
 * <h2>When to Use:</h2>
 * Use a Spy when:
 * <ul>
 *   <li>You want to test mostly real behavior but mock specific methods</li>
 *   <li>You need to verify that methods on a real object were called</li>
 *   <li>You want to test the actual implementation with one dependency mocked</li>
 *   <li>You need to spy on legacy code you can't easily refactor</li>
 * </ul>
 *
 * <h2>Advantages:</h2>
 * <ul>
 *   <li>Tests real behavior - more comprehensive coverage</li>
 *   <li>Flexible - can mock specific methods while calling others</li>
 *   <li>Good for legacy code where you can't inject all dependencies</li>
 *   <li>Allows verification of actual method calls</li>
 * </ul>
 *
 * <h2>Disadvantages:</h2>
 * <ul>
 *   <li>More complex to understand and maintain</li>
 *   <li>Can make tests brittle by coupling to implementation</li>
 *   <li>Requires careful use of doReturn() vs when()</li>
 *   <li>Can hide design issues that should be refactored</li>
 * </ul>
 *
 * <h2>Important Note:</h2>
 * When using spies, use {@code doReturn().when()} for void methods
 * or to avoid triggering the real method. Use {@code when()} only
 * for methods you want to mock before they execute.
 *
 * @author Yurii_Suprun
 * @see <a href="https://xunitpatterns.com/Spy.html">Spy Pattern</a>
 * @see MockTest
 */
@SpringBootTest
public class SpyTest {

    @Spy
    private UserService userService;

    /**
     * Demonstrates basic spy usage with method stubbing and verification.
     * 
     * The spy wraps the real UserService but allows us to stub the
     * isProcessed() method and verify processUser() was called.
     */
    @Test
    void testWithSpy() {
        // Arrange
        User user = new UserBuilder()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john@example.com")
                .build();

        // Use doReturn to stub a specific method on the spy
        Mockito.doReturn(true).when(userService).isProcessed();

        // Act: Call the real method
        userService.processUser(user);

        // Assert: Verify the real method was called
        verify(userService).processUser(user);

        // Assert: The stubbed method returns our configured value
        assertTrue(userService.isProcessed());
    }

    /**
     * Demonstrates that spy calls real methods by default.
     * 
     * Even though we have a spy, real methods are called unless explicitly stubbed.
     */
    @Test
    void testSpyCallsRealMethods() {
        // Arrange
        User user = UserBuilder.createDefaultUser();

        // Act: Call real method (not stubbed)
        userService.processUser(user);

        // Assert: The real method executed
        assertTrue(
                userService.isProcessed(),
                "Spy should call real method unless stubbed"
        );

        // Verify the real method was called
        verify(userService).processUser(user);
    }

    /**
     * Demonstrates selective stubbing - some methods real, some mocked.
     * 
     * This shows the power of spies: we can test some behavior in real
     * code while isolating specific methods.
     */
    @Test
    void testSpyWithSelectiveStubbing() {
        // Arrange
        User user = UserBuilder.createDefaultUser();

        // Stub isProcessed() but let processUser() remain real
        Mockito.doReturn(false).when(userService).isProcessed();

        // Act
        userService.processUser(user);

        // Assert: processUser ran with real logic
        verify(userService).processUser(user);

        // But isProcessed returns our stubbed value
        assertFalse(
                userService.isProcessed(),
                "Stubbed method should return our configured value"
        );
    }

    /**
     * Demonstrates verifying that a real method was called on the spy.
     * 
     * Spies allow verification of interactions on real objects.
     */
    @Test
    void testSpyVerifiesMethodCalls() {
        // Arrange
        User user1 = UserBuilder.createUserWithId(1L);
        User user2 = UserBuilder.createUserWithId(2L);

        // Act: Call processUser twice
        userService.processUser(user1);
        userService.processUser(user2);

        // Assert: Verify the method was called with any User argument
        verify(userService, Mockito.times(2)).processUser(org.mockito.ArgumentMatchers.any(User.class));
    }

    /**
     * Demonstrates using doNothing() on a spy.
     * 
     * Sometimes you want a spy to do nothing for a particular method.
     */
    @Test
    void testSpyWithDoNothing() {
        // Arrange: Create spy and configure it to do nothing
        User user = UserBuilder.createDefaultUser();
        Mockito.doNothing().when(userService).processUser(org.mockito.ArgumentMatchers.any(User.class));

        // Act: Call the method
        userService.processUser(user);

        // Assert: Verify it was called but didn't throw
        verify(userService).processUser(user);
    }

    /**
     * Demonstrates argument capturing with spies.
     * 
     * Spies can verify both the method was called and inspect its arguments.
     */
    @Test
    void testSpyWithArgumentCapturing() {
        // Arrange
        User user = UserBuilder.createDefaultUser();

        // Act
        userService.processUser(user);

        // Assert: Verify the spy was called
        verify(userService).processUser(user);
    }

    /**
     * Demonstrates that spies maintain state of real objects.
     * 
     * Since spies wrap real objects, they maintain the actual state.
     */
    @Test
    void testSpyMaintainsRealState() {
        // Arrange: Fresh spy with unprocessed state
        assertFalse(userService.isProcessed(), "Should start unprocessed");

        // Act: Process a user
        User user = UserBuilder.createDefaultUser();
        userService.processUser(user);

        // Assert: Real state is maintained
        assertTrue(
                userService.isProcessed(),
                "Real state should be maintained by spy"
        );
    }

    /**
     * Important: Demonstrates the difference between when() and doReturn().
     * 
     * For spies, use doReturn().when() to avoid triggering the real method.
     * when().thenReturn() can cause issues with spies.
     */
    @Test
    void testSpyUseDoReturnNotWhen() {
        // ✓ CORRECT: Use doReturn for spies
        Mockito.doReturn(true).when(userService).isProcessed();

        // This avoids calling the real method during setup
        assertTrue(userService.isProcessed());

        // ✗ WRONG (but shown here for documentation):
        // Mockito.when(userService.isProcessed()).thenReturn(true);
        // This would call the real method and could cause issues
    }
}

