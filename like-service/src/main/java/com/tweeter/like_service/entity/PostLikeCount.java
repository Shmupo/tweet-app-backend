package com.tweeter.like_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "post_likes_count"
)
public class PostLikeCount {
    @Id
    Long postId;

    @Min(value = 0, message = "Like value cannot be negative.")
    Long likeCount;
}
