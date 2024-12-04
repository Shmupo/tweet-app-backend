package com.tweeter.like_service.service;

import com.tweeter.like_service.entity.PostLikeCount;

public interface PostLikeCountService {
    PostLikeCount getPostLikeCountByPostId(Long postId);
    PostLikeCount addPostLikeCount(Long postId);
    PostLikeCount updatePostLikeCount(Long postId, PostLikeCount postLikeCount);
    void deletePostLikeCount(Long postId);
}
