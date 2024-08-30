package com.tweeter.user_service.repository;

import com.tweeter.user_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
