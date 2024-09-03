package com.tweeter.like_service.repository;

import com.tweeter.like_service.entity.Like;
import com.tweeter.like_service.entity.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, LikeId> {
    List<Like> findAllLikesByLikeIdPostId(Long postId);
    List<Like> findAllLikesByLikeIdUserId(Long userId);
}
