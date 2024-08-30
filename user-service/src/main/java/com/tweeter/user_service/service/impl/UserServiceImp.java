package com.tweeter.user_service.service.impl;

import com.tweeter.user_service.entity.User;
import com.tweeter.user_service.exception.ResourceNotFoundException;
import com.tweeter.user_service.repository.UserRepository;
import com.tweeter.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("user", "username", username);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("user", "email", email);
        }
        return user;
    }

    @Override
    public User getUserByUsernameOrEmail(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user == null) {
            throw new ResourceNotFoundException("user", "usernameOrEmail", usernameOrEmail);
        }
        return user;
    }

    @Override
    public String getUsernameById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException("user", "id", userId);
        });
        return user.getUsername();
    }

    @Override
    public Boolean usernameAlreadyExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean usernameOrEmailAlreadyExists(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        User foundUser = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

        foundUser.setEmail(user.getEmail());
        foundUser.setUsername(user.getUsername());
        foundUser.setImgName(user.getImgName());
        // need password encoder
        //foundUser.setPassword();

        return userRepository.save(foundUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
