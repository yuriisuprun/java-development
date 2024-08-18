package com.suprun;

import com.suprun.model.User;
import com.suprun.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Yurii_Suprun
 */
@SpringBootTest
public class DummyTest {

    @Test
    void testWithDummyObject() {
        UserService userService = new UserService();
        // dummy object, not really used in the test
        User dummyUser = new User(2L, "dummyUser", "dummy@example.com");
        userService.processUser(dummyUser);
        // the test does not care about dummyUser
        assertTrue(userService.isProcessed());
    }
}