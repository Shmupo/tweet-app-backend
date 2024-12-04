package com.tweeter.like_service.repository;

import com.tweeter.like_service.entity.PostLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeCountRepository extends JpaRepository<PostLikeCount, Long> {
}
