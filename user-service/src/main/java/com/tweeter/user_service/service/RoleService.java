package com.tweeter.user_service.service;

import com.tweeter.user_service.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getRoleByName(String name);
}
