package com.tweeter.reply_service.controller;

import com.tweeter.reply_service.entity.Reply;
import com.tweeter.reply_service.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/replies")
@RestController
public class ReplyController {
    @Autowired
    private ReplyService replyService;

//    // for testing purposes
//    @GetMapping("/posts/allReplies")
//    public ResponseEntity<List<Reply>> getAllReplies() {
//        List<Reply> data = replyService.getAllReplies();
//        return ResponseEntity.ok(data);
//    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Reply>> getAllRepliesByPostId(@PathVariable("postId") Long postId) {
        List<Reply> data = replyService.getAllRepliesByPostId(postId);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Reply> createReply(@PathVariable("postId") Long postId,
                                              @Validated  @RequestBody Reply reply) {
        Reply data = replyService.createReply(postId, reply);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}/reply/{replyId}")
    public ResponseEntity<Reply> updateReply(@PathVariable("postId") Long postId,
                                              @PathVariable("replyId") Long replyId,
                                              @Validated @RequestBody Reply reply) {
        Reply data = replyService.updateReply(postId, replyId, reply);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{postId}/reply/{replyId}")
    public HttpStatus deleteReply(@PathVariable("postId") Long postId,
                                   @PathVariable("replyId") Long replyId) {
        replyService.deleteReply(postId, replyId);
        return HttpStatus.OK;
    }
}
