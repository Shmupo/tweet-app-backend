package com.tweeter.user_service.controller;

import com.tweeter.user_service.entity.User;
import com.tweeter.user_service.security.JWTTokenProvider;
import com.tweeter.user_service.service.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTokenProvider tokenProvider;

    @GetMapping("/profile")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if(tokenProvider.validateToken(token)) {
            String usernameOrEmail = tokenProvider.getUsername(token);
            User foundUser = userService.getUserByUsernameOrEmail(usernameOrEmail);
            return ResponseEntity.ok(foundUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/profile/image/{fileName}")
    public HttpStatus updateUserImage(@RequestHeader("Authorization") String authHeader,
                                                @PathVariable("fileName") String fileName) {
        String token = authHeader.substring(7);
        if(tokenProvider.validateToken(token)) {
            String usernameOrEmail = tokenProvider.getUsername(token);
            User foundUser = userService.getUserByUsernameOrEmail(usernameOrEmail);
            foundUser.setImgName(fileName);
            userService.updateUser(foundUser.getId(), foundUser);
            return HttpStatus.OK;
        } else {
            return HttpStatus.UNAUTHORIZED;
        }
    }

    @GetMapping("/id/username/{userId}")
    public ResponseEntity<String> getUsernameById(@PathVariable("userId") Long userId) {
        String username = userService.getUsernameById(userId);
        return ResponseEntity.ok(username);
    }

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

    @GetMapping("/usernameOrEmail/{usernameOrEmail}")
    public ResponseEntity<User> getUserByUsernameOrEmail(@PathVariable("usernameOrEmail") String usernameOrEmail) {
        User data = userService.getUserByUsernameOrEmail(usernameOrEmail);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/username/{username}/exists")
    public ResponseEntity<Boolean> userAlreadyExists(@PathVariable("username") String username) {
        Boolean data = userService.usernameAlreadyExists(username);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/email/{email}/exists")
    public ResponseEntity<Boolean> emailAlreadyExists(@PathVariable("email") String email) {
        Boolean data = userService.emailAlreadyExists(email);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/usernameOrEmail/{usernameOrEmail}/exists")
    public ResponseEntity<Boolean> usernameOrEmailExists(@PathVariable("usernameOrEmail") String usernameOrEmail) {
        Boolean data = userService.usernameOrEmailAlreadyExists(usernameOrEmail, usernameOrEmail);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/id/{userId}/image")
    public ResponseEntity<Resource> getUserImage(@PathVariable("userId") Long id) {
        User foundUser = userService.getUserById(id);
        String imageFileName = foundUser.getImgName();
        Resource imageResource = new ClassPathResource("static/images/" + imageFileName);

        if (!imageResource.exists()) {
            System.out.println("Image not found: " + imageResource.getFilename());
            return ResponseEntity.notFound().build();
        }

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imageFileName + "\"")
                    .body(imageResource);
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getUserImageName(@PathVariable("fileName") String filename) {
        Resource imageResource = new ClassPathResource("static/images/" + filename);

        if (!imageResource.exists()) {
            System.out.println("Image not found: " + imageResource.getFilename());
            return ResponseEntity.notFound().build();
        }

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(imageResource);
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        User data = userService.createUser(user);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
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
