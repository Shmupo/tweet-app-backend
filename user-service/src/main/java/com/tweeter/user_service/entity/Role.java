package com.tweeter.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // for getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name="roles"
)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
