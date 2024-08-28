package com.tweeter.user_service.controller;

import com.tweeter.user_service.entity.User;
import com.tweeter.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> data = userService.getAllUsers();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        User data = userService.getUserById(userId);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User data = userService.getUserByUsername(username);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User data = userService.getUserByEmail(email);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/username/{username}/exists")
    public ResponseEntity<Boolean> userAlreadyExists(@PathVariable("username") String username) {
        Boolean data = userService.usernameAlreadyExists(username);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/email/{email}/exists")
    public ResponseEntity<Boolean> emailAlreadyExists(@PathVariable("username") String email) {
        Boolean data = userService.usernameAlreadyExists(email);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        User data = userService.createUser(user);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId,
                                           @Validated @RequestBody User user) {
        User data = userService.updateUser(userId, user);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{userId}")
    public HttpStatus deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return HttpStatus.OK;
    }
}
