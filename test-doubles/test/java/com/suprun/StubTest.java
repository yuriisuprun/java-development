package com.suprun;

import com.suprun.model.User;
import com.suprun.repository.UserRepository;
import com.suprun.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Yurii_Suprun
 */
@SpringBootTest
public class StubTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testWithStub() {
        User user = new User(1L, "John Doe", "john@example.com");

        // stub the repository method
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(1L);
        assertTrue(retrievedUser.isPresent());
        assertEquals("John Doe", retrievedUser.get().getName());
    }
}
