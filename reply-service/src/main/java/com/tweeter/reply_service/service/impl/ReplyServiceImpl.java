package com.tweeter.reply_service.service.impl;

import com.tweeter.reply_service.entity.Reply;
import com.tweeter.reply_service.exception.ResourceNotFoundException;
import com.tweeter.reply_service.payload.Post;
import com.tweeter.reply_service.repository.ReplyRepository;
import com.tweeter.reply_service.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    @Override
    public List<Reply> getAllRepliesByPostId(Long postId) {
        return replyRepository.findAllRepliesByPostId(postId);
    }

    @Override
    public Reply getReplyById(Long replyId) {
        return replyRepository.findById(replyId).
                orElseThrow(() -> new ResourceNotFoundException("reply", "replyId", replyId));
    }

    @Override
    public Reply createReply(Long postId, Reply reply) {
        // check if the post exists
        Post foundPost = restTemplate.getForObject("http://POST-SERVICE/api/posts/" + postId, Post.class);
        if (foundPost == null) {
            throw new ResourceNotFoundException("post", "postId", postId);
        }

        reply.setPostId(postId);
        return replyRepository.save(reply);
    }

    @Override
    public Reply updateReply(Long postId, Long replyId, Reply reply) {
        // check if post exists
        Post foundPost = restTemplate.getForObject("http://POST-SERVICE/api/posts/" + postId, Post.class);
        if (foundPost == null) {
            throw new ResourceNotFoundException("post", "postId", postId);
        }

        Reply foundReply = replyRepository.findById(replyId).
                orElseThrow(() -> new ResourceNotFoundException("reply", "replyId", replyId));

        foundReply.setUserId(reply.getUserId());
        foundReply.setPostId(reply.getPostId());
        foundReply.setContent(reply.getContent());
        foundReply.setTag(reply.getTag());

        return replyRepository.save(foundReply);
    }

    @Override
    public void deleteReply(Long postId, Long replyId) {
        // check if post exists
        Post foundPost = restTemplate.getForObject("http://POST-SERVICE/api/posts/" + postId, Post.class);
        if (foundPost == null) {
            throw new ResourceNotFoundException("post", "postId", postId);
        }

        replyRepository.deleteById(replyId);
    }
}
