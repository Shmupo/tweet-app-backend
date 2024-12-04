package com.tweeter.hashtag_service.service.impl;

import com.tweeter.hashtag_service.entity.Hashtag;
import com.tweeter.hashtag_service.entity.PostHashtag;
import com.tweeter.hashtag_service.entity.PostHashtagId;
import com.tweeter.hashtag_service.exception.ResourceNotFoundException;
import com.tweeter.hashtag_service.repository.HashtagRepository;
import com.tweeter.hashtag_service.repository.PostHashtagRepository;
import com.tweeter.hashtag_service.service.HashtagService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HashtagServiceImpl implements HashtagService {
    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private PostHashtagRepository postHashtagRepository;

    @Override
    public List<Long> getAllPostsOfHashtag(Long id) {
        return List.of();
    }

    @Override
    @Transactional
    public PostHashtag addPostIdToHashtag(String hashtagName, Long postId) {
        try {
            Hashtag foundHashtag = hashtagRepository.findHashtagByHashtagName(hashtagName);

            // create new hashtag if this one does not yet exist
            if (foundHashtag == null) {
                foundHashtag = createHashtag(hashtagName);
            }

            // hashtag-post relation already exists
            Optional<PostHashtag> existingPostHashtag = postHashtagRepository
                    .findById(new PostHashtagId(postId, foundHashtag.getId()));
            if (existingPostHashtag.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "This post is already associated with the hashtag");
            }

            PostHashtag postHashtag = new PostHashtag(new PostHashtagId(postId, foundHashtag.getId()));

            return postHashtagRepository.save(postHashtag);
        } catch (DataIntegrityViolationException e) {
            // Handle data integrity violations (e.g., constraint violations)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Database error occurred while adding the post to the hashtag: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // Handle case when hashtag already exists or input is invalid
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            // Catch any other unforeseen errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred while associating the post with the hashtag: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Hashtag createHashtag(String hashtagName) {
        try {
            // Check if the hashtag already exists to avoid uniqueness violation
            if (hashtagRepository.findHashtagByHashtagName(hashtagName) != null) {
                throw new IllegalArgumentException("Hashtag already exists: " + hashtagName);
            }

            Hashtag hashtag = new Hashtag(hashtagName);

            return hashtagRepository.save(hashtag);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Failed to create hashtag due to data integrity violation: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating the hashtag: " + e.getMessage(), e);
        }
    }

    @Override
    public Hashtag updateHashtag(Long id, Hashtag hashtag) {
        Hashtag foundHashtag = hashtagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("hashtag", "hashtagId", id));

        // Update the hashtag name or other fields
        foundHashtag.setHashtagName(hashtag.getHashtagName());

        // Save the updated Hashtag to the repository
        return hashtagRepository.save(foundHashtag);
    }

    @Override
    public void deleteHashtag(Long id) {
        hashtagRepository.deleteById(id);
    }
}
