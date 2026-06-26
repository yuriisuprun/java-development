package com.suprun;

import com.suprun.model.User;
import com.suprun.service.UserService;
import com.suprun.testutil.UserBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test demonstrating the Dummy test double pattern.
 *
 * <h2>Pattern Description:</h2>
 * A Dummy is a test double that is passed around but never actually used.
 * It exists only to satisfy method signatures and parameter requirements.
 *
 * <h2>When to Use:</h2>
 * Use a Dummy when:
 * <ul>
 *   <li>An object is required by a method signature but not needed for the test</li>
 *   <li>You want to test behavior that doesn't depend on the object's state</li>
 *   <li>The object is simply passed through to another component</li>
 * </ul>
 *
 * <h2>Advantages:</h2>
 * <ul>
 *   <li>Simple - minimal setup required</li>
 *   <li>No mocking framework needed</li>
 *   <li>Clear intent - shows what's not being tested</li>
 * </ul>
 *
 * <h2>Disadvantages:</h2>
 * <ul>
 *   <li>Can lead to unclear test intent if overused</li>
 *   <li>Does not help verify behavior</li>
 * </ul>
 *
 * @author Yurii_Suprun
 * @see <a href="https://xunitpatterns.com/Dummy%20Object.html">Dummy Object Pattern</a>
 */
@SpringBootTest
public class DummyTest {

    /**
     * Demonstrates using a Dummy object.
     *
     * The User object passed to processUser is not actually used in the test,
     * but is required by the method signature. The test verifies that the
     * service successfully processes regardless of the user data provided.
     */
    @Test
    void testWithDummyObject() {
        // Arrange: Create service and dummy user (not used in test)
        UserService userService = new UserService();
        User dummyUser = new UserBuilder()
                .withId(999L)
                .withName("Dummy")
                .withEmail("dummy@test.com")
                .build();

        // Act: Call method with dummy object
        userService.processUser(dummyUser);

        // Assert: Verify that processing occurred, independent of dummy data
        assertTrue(
                userService.isProcessed(),
                "Service should have processed the request regardless of input"
        );
    }

    /**
     * Another example showing multiple dummies.
     *
     * This demonstrates that dummies are about satisfying the API contract,
     * not about testing the object itself.
     */
    @Test
    void testProcessingDoesNotDependOnUserData() {
        UserService userService = new UserService();
        
        // Multiple dummies - all ignored, just need something to pass
        User dummy1 = UserBuilder.createUserWithId(100L);
        User dummy2 = UserBuilder.createDefaultUser();

        userService.processUser(dummy1);
        assertTrue(userService.isProcessed());

        // Reset and test with another dummy - same result
        userService = new UserService();
        userService.processUser(dummy2);
        assertTrue(userService.isProcessed());
    }
}
