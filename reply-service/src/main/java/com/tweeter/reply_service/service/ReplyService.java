package com.tweeter.reply_service.service;

import com.tweeter.reply_service.entity.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getAllReplies();
    List<Reply> getAllRepliesByPostId(Long postId);
    Reply getReplyById(Long replyId);
    Reply createReply(Long postId, Reply reply);
    Reply updateReply(Long postId, Long replyId, Reply reply);
    void deleteReply(Long postId, Long replyId);
}
