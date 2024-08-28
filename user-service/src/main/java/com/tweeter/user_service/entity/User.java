package com.tweeter.user_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "users"
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull(message = "Username cannot be null")
    @Size(max = 32, min = 4, message = "Username must be between 4 and 32 characters")
    String username;
    @NotNull(message = "Email cannot be null")
    String email;
    @Size(max = 32, min = 8, message = "Password must be between 8 and 64 characters")
    String password;
    // used to grab image from front end
    String imgName;
}
