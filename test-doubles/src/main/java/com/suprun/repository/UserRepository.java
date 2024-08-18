package com.suprun.repository;

import com.suprun.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yurii_Suprun
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
