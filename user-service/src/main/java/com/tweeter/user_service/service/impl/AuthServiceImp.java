package com.tweeter.user_service.service.impl;

import com.tweeter.user_service.entity.Role;
import com.tweeter.user_service.entity.User;
import com.tweeter.user_service.payload.LoginDto;
import com.tweeter.user_service.payload.RegisterDto;
import com.tweeter.user_service.repository.RoleRepository;
import com.tweeter.user_service.repository.UserRepository;
import com.tweeter.user_service.security.JWTTokenProvider;
import com.tweeter.user_service.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);

            System.out.println("User logged in successfully: " + loginDto.getUsernameOrEmail());

            return token;

        } catch (HttpClientErrorException e) {
            System.err.println(e);
            throw new RuntimeException("Service error during login.");
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("Unexpected error during login.");
        }
    }

    @Transactional
    @Override
    public String register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String email = registerDto.getEmail();

        try {
            Boolean userExists = userRepository.existsByUsernameOrEmail(username, email);

            if (userExists) {
                throw new RuntimeException("Username or email already exists.");
            }

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            Set<Role> roles = new HashSet<>();
            Role role = roleRepository.findByName("ROLE_USER");
            roles.add(role);
            user.setRoles(roles);

            userRepository.save(user);

            return "User registered successfully.";
        } catch (HttpClientErrorException e) {
            System.err.println(e);
            throw new RuntimeException("Service error during registration.");
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("Unexpected error during registration.");
        }
    }
}
