package com.tweeter.user_service.service;

import com.tweeter.user_service.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByUsernameOrEmail(String usernameOrEmail);
    String getUsernameById(Long userId);
    Boolean usernameAlreadyExists(String username);
    Boolean emailAlreadyExists(String email);
    Boolean usernameOrEmailAlreadyExists(String username, String email);
    User createUser(User user);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
}
