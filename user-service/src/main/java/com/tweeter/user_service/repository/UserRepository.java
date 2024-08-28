package com.tweeter.user_service.repository;

import com.tweeter.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String Username);
    Boolean existsByEmail(String Username);
}
