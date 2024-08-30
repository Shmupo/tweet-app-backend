package com.tweeter.user_service.controller;

import com.tweeter.user_service.entity.Role;
import com.tweeter.user_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users/roles")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{roleName}")
    public ResponseEntity<Role> getRoleByName(@PathVariable("roleName") String roleName) {
        Role role = roleService.getRoleByName(roleName);
        if (role == null) {
            return ResponseEntity.notFound().build(); // Return 404 if role is not found
        }
        return ResponseEntity.ok(role); // Return 200 OK with the role
    }
}
