package com.tweeter.user_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    private Long id;
    @NotNull(message = "Username cannot be null")
    @Size(max = 32, min = 4, message = "Username must be between 4 and 32 characters")
    private String username;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    // used to grab image
    private String imgName = null;

    @ManyToMany(
            fetch = FetchType.EAGER, // tries to get all connected records
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Role> roles;
}
