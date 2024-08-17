package com.suprun.service;

import com.suprun.model.User;

/**
 * @author Yurii_Suprun
 */
public class UserService {
    private boolean processed = false;

    public void processUser(User user) {
        // Simulate processing the user
        processed = true;
    }

    public boolean isProcessed() {
        return processed;
    }
}
