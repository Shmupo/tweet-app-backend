package com.tweeter.user_service.service;

import com.tweeter.user_service.payload.LoginDto;
import com.tweeter.user_service.payload.RegisterDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
