package com.tweeter.user_service.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "Username or email is required")
    @Size(max = 32, min = 4, message = "Username must be between 4 and 32 characters")
    private String usernameOrEmail;
    @NotBlank(message = "Password is required")
    private String password;
}
