package com.socialapp.service;

import com.socialapp.entity.Comment;
import com.socialapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getExpiredAudioComments() {
        return commentRepository.findByExpiresAtBefore(LocalDateTime.now());
    }

    public List<Comment> getCommentsByPostId(int postId){
      return  commentRepository.findByPostId(postId);
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
