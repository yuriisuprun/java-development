package com.suprun;

import com.suprun.model.User;
import com.suprun.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Yurii_Suprun
 */
@SpringBootTest
public class SpyTest {

    @Spy
    private UserService userService;

    @Test
    void testWithSpy() {
        User user = new User(1L, "John Doe", "john@example.com");

        // use spy to partially mock the object
        Mockito.doReturn(true).when(userService).isProcessed();

        userService.processUser(user);

        // verify that processUser method was called
        Mockito.verify(userService).processUser(user);

        // asserts the spy's behavior
        assertTrue(userService.isProcessed());
    }
}

