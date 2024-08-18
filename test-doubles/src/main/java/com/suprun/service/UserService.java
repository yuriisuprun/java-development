package com.suprun.service;

import com.suprun.model.User;
import com.suprun.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author Yurii_Suprun
 */
@Service
public class UserService {
    private boolean processed = false;
    private UserRepository userRepository;

    public UserService() {}

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void processUser(User user) {
        // simulate processing the user
        processed = true;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void saveUser(User user) {
        if (userRepository != null) {
            userRepository.save(user);
        } else {
            throw new IllegalStateException("UserRepository not initialized");
        }
    }
}
