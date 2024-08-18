package com.suprun;

import com.suprun.model.User;
import com.suprun.repository.FakeUserRepository;
import com.suprun.repository.UserRepository;
import com.suprun.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Yurii_Suprun
 */
@SpringBootTest
public class FakeTest {

    @Test
    void testWithFakeObject() {
        UserRepository fakeRepository = new FakeUserRepository();
        UserService userService = new UserService(fakeRepository);

        User user = new User(1L, "John Doe", "john@example.com");
        userService.saveUser(user);

        Optional<User> retrievedUser = fakeRepository.findById(1L);
        assertTrue(retrievedUser.isPresent());
        assertEquals("John Doe", retrievedUser.get().getName());
    }
}
