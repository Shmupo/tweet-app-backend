package com.tweeter.post_service.repository;

import com.tweeter.post_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllPostsByUserId(Long userId);
    List<Post> findAllPostsByTag(String tag);
}
