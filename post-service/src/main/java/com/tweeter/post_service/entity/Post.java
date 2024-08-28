package com.tweeter.post_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "posts"
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 64, min = 8, message = "Title must be between 8 and 64 characters.")
    private String title;

    @NotNull
    @Size(max = 144, min = 8, message = "Content must be between 8 and 144 characters.")
    private String content;

    @Size(max = 50, message = "Tag cannot exceed 50 characters.")
    private String tag = null;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    private Set<Long> likedByUserIds = new HashSet<>();
}
