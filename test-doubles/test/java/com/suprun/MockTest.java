package com.suprun;

import com.suprun.model.User;
import com.suprun.repository.UserRepository;
import com.suprun.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yurii_Suprun
 */
@SpringBootTest
public class MockTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testWithMock() {
        User user = new User(1L, "John Doe", "john@example.com");

        // mock the save method to return the same user
        Mockito.when(userRepository.save(user)).thenReturn(user);

        userService.saveUser(user);

        // verify that save method was called
        Mockito.verify(userRepository).save(user);
    }
}
