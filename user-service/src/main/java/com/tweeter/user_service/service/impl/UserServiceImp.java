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
