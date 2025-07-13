package com.socialapp.repository;

import com.socialapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByExpiresAtBefore(LocalDateTime time);

    public List<Comment> findByPostId();
}