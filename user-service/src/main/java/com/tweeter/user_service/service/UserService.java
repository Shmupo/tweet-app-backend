package com.tweeter.user_service.service;

import com.tweeter.user_service.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
}
