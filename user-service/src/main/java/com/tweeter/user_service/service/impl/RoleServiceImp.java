package com.tweeter.user_service.service.impl;

import com.tweeter.user_service.entity.Role;
import com.tweeter.user_service.exception.ResourceNotFoundException;
import com.tweeter.user_service.repository.RoleRepository;
import com.tweeter.user_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String roleName) {
        Role role = roleRepository.findByName(roleName);

        if (role == null) {
            throw new ResourceNotFoundException("role", "name", roleName);
        }

        return role;
    }
}
