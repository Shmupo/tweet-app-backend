package com.tweeter.like_service.service;

import com.tweeter.like_service.entity.Like;

import java.util.List;

public interface LikeService {
    Long getTotalLikesByPostId(Long postId);
    List<Like> getLikesByUserId(Long userId);
    Like getLikeByUserIdAndPostId(Long userId, Long postId);
    Like createLike(Long userId, Long postId);
    void deleteLike(Long userId, Long postId);
}
