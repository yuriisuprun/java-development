package com.suprun.repository;

import com.suprun.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yurii_Suprun
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
