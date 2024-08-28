package com.tweeter.reply_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "replies"
)
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    Long userId;
    @NotNull
    Long postId;

    @NotNull
    @Size(max = 144, min = 1, message = "Content must be between 1 and 144 characters.")
    String content;

    @Size(max = 50, min = 1, message = "Tag must be between 1 and 50 characters")
    String tag;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
