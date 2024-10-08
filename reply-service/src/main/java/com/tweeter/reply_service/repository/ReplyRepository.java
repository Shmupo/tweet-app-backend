package com.tweeter.reply_service.repository;

import com.tweeter.reply_service.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllRepliesByPostId(Long postId);
}
