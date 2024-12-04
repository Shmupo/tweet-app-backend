package com.tweeter.like_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LikeId implements Serializable {
    @NonNull
    @Column(nullable = false)
    private Long userId;

    @NonNull
    @Column(nullable = false)
    private Long postId;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        LikeId likeId = (LikeId) o;
//        return Objects.equals(postId, likeId.postId) &&
//                Objects.equals(userId, likeId.userId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(postId, userId);
//    }
}
